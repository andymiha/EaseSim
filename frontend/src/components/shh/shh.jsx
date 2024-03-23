import 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import ToggleButton from '@mui/material/ToggleButton';

import './shh.css';

const SHH = () => {

    // Sample data for table rows
    const secondTableRows = [
      { id: 1, zone: 'Zone 1', rooms: 'Room A', desiredTemp: '22°C' },
      { id: 2, zone: 'Zone 2', rooms: 'Room B', desiredTemp: '20°C' },
      // Add more rows as needed
    ];

  

  

  return (
    <div>
      <h2>SHH content</h2>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }}>
          <TableHead>
            <TableRow>
              <TableCell>Room</TableCell>
              <TableCell>On/Off</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>Temperature System</TableCell>
              <TableCell>
                <ToggleButtonGroup>
                  <ToggleButton value="on">On</ToggleButton>
                  <ToggleButton value="off">Off</ToggleButton>
                </ToggleButtonGroup>
              </TableCell>
            </TableRow>
            {/* Add more rows as needed */}
          </TableBody>
        </Table>
      </TableContainer>

      <h3>Rooms: Heating and Cooling</h3>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }}>
          <TableHead>
            <TableRow>
              <TableCell>Zones</TableCell>
              <TableCell>Rooms</TableCell>
              <TableCell>Desired Temp</TableCell>
              <TableCell>Edit Cell</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {secondTableRows.map((row) => (
              <TableRow key={row.id}>
                <TableCell>{row.zone}</TableCell>
                <TableCell>{row.rooms}</TableCell>
                <TableCell>{row.desiredTemp}</TableCell>
                <TableCell>
              <input
                    type="number"
                    className="edit-temp-input" // Apply the CSS class
                  />

              </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default SHH;
