import  { useState } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';

const SHP = () => {
  const [awayModeChecked, setAwayModeChecked] = useState(false);


  // Toggle handlers
  const handleAwayModeChange = (event) => setAwayModeChecked(event.target.checked);


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
                  control={<Switch checked={awayModeChecked} onChange={handleAwayModeChange} />}
                  label={awayModeChecked ? 'On' : 'Off'}
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
      </TableRow>
    </TableHead>
    <TableBody>
      <TableRow>
        <TableCell>Call Police</TableCell>
        <TableCell>
          <input
            type="number"
            className="edit-temp-input" // Assuming you have styling under this class
          />
        </TableCell>
      </TableRow>
    </TableBody>
  </Table>
</TableContainer>

    </div>
  );
};

export default SHP;
