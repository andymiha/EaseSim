import  { useState, useEffect } from 'react';
import { Button, TableContainer, Table, TableBody, TableCell, Checkbox, TableHead, TableRow, Paper, FormControlLabel, Switch } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';

const SHP = () => {
  const [awayMode, setAwayMode] = useState(false);
  const [policeTimer, setPoliceTimer] = useState(0);
  const [newPoliceTimer, setNewPoliceTimer] = useState(0); // New state for input value
  const dispatch = useDispatch();
  const sensors = useSelector(state => state.sensors);

  const handleCheckboxChange = (event) => {
    const { name, checked } = event.target;
    dispatch({ type: 'UPDATE_SENSOR', payload: { sensorName: name, isChecked: checked }});
  };
  
  useEffect(() => {
    // Fetch away mode status
    fetch('http://localhost:8080/api/shp/get-away-mode')
      .then(response => response.json())
      .then(data => setAwayMode(data))
      .catch(error => console.error('Error fetching away mode:', error));

    // Fetch police timer
    fetch('http://localhost:8080/api/shp/get-police-timer')
      .then(response => response.json())
      .then(data => setPoliceTimer(data))
      .catch(error => console.error('Error fetching police timer:', error));
  }, []);

  const handleAwayModeChange = (event) => {
    const isChecked = event.target.checked;
    setAwayMode(isChecked);
    handleSetAwayMode(isChecked); // Assuming you have this function
  };

  const handleSetAwayMode = (isAway) => {
    fetch('http://localhost:8080/api/shp/set-away-mode', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(isAway),
    })
      .then(response => response.text())
      .then(data => {
        console.log(data)
        dispatch({ type: 'ADD_LOG', payload: data });
      })
      .catch(error => console.error('Error setting away mode:', error));
  };

  const handleSubmitPoliceTimer = () => {
    fetch('http://localhost:8080/api/shp/set-police-timer', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newPoliceTimer),
    })
      .then(response => response.text())
      .then(data => {
        console.log(data)
        dispatch({ type: 'ADD_LOG', payload: data });
      })
      .catch(error => console.error('Error setting police timer:', error));
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
      <h2>SHP Content</h2>

      {/* Away Mode Toggle Table */}
      <TableContainer component={Paper}>
        <Table aria-label="Away Mode">
          <TableBody>
            <TableRow>
              <TableCell component="th" scope="row"><h3>Away Mode Toggle</h3></TableCell>
              <TableCell align="right">
                <div style={{ display: 'flex', justifyContent: 'center'}}>
                  <FormControlLabel
                    control={<Switch checked={awayMode} onChange={handleAwayModeChange} />}
                    label={awayMode ? 'On' : 'Off'}
                  />
                </div>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>

      {/* Set Time Police Table */}
      <h3>Set Time to Call Police</h3>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }}>
          <TableHead>
            <TableRow>
              <TableCell>Police</TableCell>
              <TableCell>Set Time in Minutes</TableCell>
              <TableCell>Action</TableCell> {/* Added table cell for the submit button */}
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>Call Police</TableCell>
              <TableCell>
                <input
                  type="number"
                  className="edit-temp-input" // Assuming you have styling under this class
                  value={newPoliceTimer} // Use newPoliceTimer state for input value
                  onChange={(e) => setNewPoliceTimer(e.target.value)} // Update newPoliceTimer state on input change
                />
              </TableCell>
              <TableCell> {/* Submit button */}
                <Button onClick={handleSubmitPoliceTimer} variant="contained" color="primary">Submit</Button>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>

      <h2>Sensors</h2>
      {Object.keys(sensors).map((key) => (
        <FormControlLabel
          key={key}
          control={
            <Checkbox
              checked={sensors[key]}
              onChange={handleCheckboxChange}
              name={key}
              color="primary"
            />
          }
          label={key.replace(/([A-Z])/g, ' $1').trim()}
        />
      ))}
    </div>
  );
};

export default SHP;
