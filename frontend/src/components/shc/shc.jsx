import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, ToggleButton, ToggleButtonGroup, Box, Stack, Switch, Checkbox } from "@mui/material";
import { useState, useEffect } from "react";


const SHC = () => {
  const [lights, setLights] = useState([])
  const [doors, setDoors] = useState([])
  const [windows, setWindows] = useState([])
  

  useEffect(() => {  
    fetch('http://localhost:8080/getHouseLights') 
        .then(response => response.json())
        .then(data => {
          setLights(data)
        })
        .then(console.log("bluetooth connect assucessfully"))
        .catch(error => console.error('Error fetching data:', error));
  },
    []);

   const initialWindowsOnState = [];// hold initial window states

  useEffect(() => {  
    
    fetch('http://localhost:8080/getHouseWindows') 
        .then(response => response.json())
        .then(data => {
          setWindows(data),
        
          windows.forEach(window => 
            initialWindowsOnState[window.id] = window.state)
        })
        updateWindowsOnStates(initialWindowsOnState)
        .then(console.log("bluetooth connect assucessfully"))
        .catch(error => console.error('Error fetching data:', error));
  },
    []);


  useEffect(() => {  
    fetch('http://localhost:8080/getHouseDoors') 
        .then(response => response.json())
        .then(data => {
           setDoors(data)
        })
        .then(console.log("bluetooth connect assucessfully"))
        .catch(error => console.error('Error fetching data:', error));
   },
    []);
  

  const [rows, setRows] = useState([]);

  

  const generateRows = (data) => {
    setRows(data.map((item) => ({
      id: item.id,
      room: item.roomName,
      isOn: item.state,
      isBlocked: item.isBlocked || false,
      isAuto: item.isAuto || false,
    })));
  };

  const [alignment, setAlignment] = useState("");

  const handleAlignment = (event, newAlignment) => {
    setAlignment(newAlignment);
  };

  const handleLightChange = (row, index) => { 
    fetch('http://localhost:8080/toggleLight', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: row.id })
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error toggling: ' + response.statusText);
        }
    })
    .then(data => {
        console.log(data);
        setRows(prevRows => 
            prevRows.map((row, i) =>
                i === index ? {...row, isOn: data.state} : row
            )
        );
    })
    .catch(error => {
        console.error('Error toggling:', error);
    });
};

const handleDoorChange = (row, index) => { 
  fetch('http://localhost:8080/toggleDoor', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id: row.id })
  })
  .then(response => {
      if (response.ok) {
          return response.json();
      } else {
          throw new Error('Error toggling: ' + response.statusText);
      }
  })
  .then(data => {
      console.log(data);
      setRows(prevRows => 
          prevRows.map((row, i) =>
              i === index ? {...row, isOn: data.state} : row
          )
      );
  })
  .catch(error => {
      console.error('Error toggling:', error);
  });
};

const handleWindowChange = (row, index) => { 
  fetch('http://localhost:8080/toggleWindow', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id: row.id })
  })
  .then(response => {
      if (response.ok) {
          return response.json();
      } else {
          throw new Error('Error toggling: ' + response.statusText);
      }
  })
  .then(data => {
      console.log(data);
      setRows(prevRows => 
          prevRows.map((row, i) =>
              i === index ? {...row, isOn: data.state} : row
          )
      );
  
  })
  updateWindowsOnStates(row.id, row.state) 
  .catch(error => {
      console.error('Error toggling:', error);
  });
};

const handleLightAutoChange = (row, index) => {

    fetch('http://localhost:8080/toggleIsAutoLight', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id: row.id })
  })
  .then(response => {
      if (response.ok) {
          return response.json();
      } else {
          throw new Error('Error toggling: ' + response.statusText);
      }
  })
  .then(data => {
      console.log(data);
      setRows ((prevRows) => 
      prevRows.map((row, i) =>
        i === index ? {...row, isAuto: !row.isAuto} : row
      )
      );
  })
  .catch(error => {
      console.error('Error toggling:', error);
  });
    
  };

  const handleDoorAutoChange = (row, index) => {
    console.log(row)
    fetch('http://localhost:8080/toggleIsAutoDoor', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id: row.id })
  })
  .then(response => {
      if (response.ok) {
          return response.json();
      } else {
          throw new Error('Error toggling: ' + response.statusText);
      }
  })
  .then(data => {
      console.log(data);
      setRows ((prevRows) => 
      prevRows.map((row, i) =>
        i === index ? {...row, isAuto: !row.isAuto} : row
      )
      );
  })
  .catch(error => {
      console.error('Error toggling:', error);
  });
    
  };


  return (
    <Box>
      <Stack
       direction="column"
       justifyContent="center"
       alignItems="center"
       spacing={3}
      >
        <ToggleButtonGroup
          value={alignment}
          exclusive
          onChange={handleAlignment}
          aria-label="text alignment"
        >
          <ToggleButton value="left" onClick={() => generateRows(lights)}>
            Lights
          </ToggleButton>
          <ToggleButton value="center" onClick={() => generateRows(doors)}>
            Doors
          </ToggleButton>
          <ToggleButton value="right" onClick={() => generateRows(windows)}>
            Windows
          </ToggleButton>
        </ToggleButtonGroup>
        <Paper sx={{ width: '100%', overflow: 'hidden' }}>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 , maxHeight: 440}} >
            <TableHead>
              <TableRow>
           {/* isAlighnment == "center" */}
                 <TableCell>Room</TableCell>
                <TableCell>On/Off</TableCell>
                {alignment !== "right" && <TableCell>Auto-mode</TableCell>}
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row, index) => (
                <TableRow
                  key={index}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell>{row.room}</TableCell>
                  <TableCell>
                  {alignment === "left" && (<Switch
                    checked={row.isOn}
                    onChange={() => handleLightChange(row, index)}
                    disabled = {row.isBlocked || row.isAuto}
                    />)}
                   {alignment === "center" && (<Switch
                    checked={row.isOn}
                    onChange={() => handleDoorChange(row, index)}
                    disabled = {row.isBlocked || row.isAuto}
                    />)}
                  {alignment === "right" && (<Switch
                    checked={row.isOn}
                    onChange={() => handleWindowChange(row, index)}
                    disabled = {row.isBlocked || row.isAuto}
                    />)}
                    {row.isOn}</TableCell>
              
                  <TableCell>
                  {alignment === "left" && (<Checkbox
                        checked={row.isAuto}
                        onChange = {() => handleLightAutoChange (row,index)}
                      />)}
                
                   {alignment === "center" && (<Checkbox
                        checked={row.isAuto}
                        onChange = {() => handleDoorAutoChange (row,index)}
                      />)}
                {row.isAuto}
                </TableCell>
                </TableRow>
                ))}
            </TableBody>
          </Table>
        </TableContainer>
        </Paper>
      </Stack>
    </Box>
  );
}
export default SHC;