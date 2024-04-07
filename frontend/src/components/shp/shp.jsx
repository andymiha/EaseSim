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
  const [doorsChecked, setDoorsChecked] = useState(false);
  const [windowsChecked, setWindowsChecked] = useState(false);
  const [motionSensorsChecked, setMotionSensorsChecked] = useState(false);

  // Toggle handlers
  const handleAwayModeChange = (event) => setAwayModeChecked(event.target.checked);
  const handleDoorsChange = (event) => setDoorsChecked(event.target.checked);
  const handleWindowsChange = (event) => setWindowsChecked(event.target.checked);
  const handleMotionSensorsChange = (event) => setMotionSensorsChecked(event.target.checked);

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

      {/* Adjusted to ensure toggles are under "Status" */}
      {/* Doors Status Table */}
      <TableContainer component={Paper}>
        <Table aria-label="Door Status">
          <TableHead>
            <TableRow>
              <TableCell>Doors</TableCell>
              <TableCell align="right">Status</TableCell>
              <TableCell align="right">Notification Type</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>Data 1</TableCell>
              <TableCell align="right">

              <div style={{ display: 'flex', justifyContent: 'center'}}>
                <FormControlLabel
                  control={<Switch checked={doorsChecked} onChange={handleDoorsChange} />}
                  label=""
                />
                </div>

              </TableCell>
              <TableCell align="right">Email</TableCell>
            </TableRow>
            {/* Additional rows for other doors */}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Windows Status Table */}
      <TableContainer component={Paper}>
        <Table aria-label="Window Status">
          <TableHead>
            <TableRow>
              <TableCell>Windows</TableCell>
              <TableCell align="right">Status</TableCell>
              <TableCell align="right">Notification Type</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>Data 2</TableCell>
              <TableCell align="right">

              <div style={{ display: 'flex', justifyContent: 'center' }}>
                <FormControlLabel
                  control={<Switch checked={windowsChecked} onChange={handleWindowsChange} />}
                  label=""
                />
                </div>
              </TableCell>
              <TableCell align="right">SMS</TableCell>
            </TableRow>
            {/* Additional rows for other windows */}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Motion Sensor Status Table */}
      <TableContainer component={Paper}>
        <Table aria-label="Motion Sensor Status">
          <TableHead>
            <TableRow>
              <TableCell>Sensors</TableCell>
              <TableCell align="right">Status</TableCell>
              <TableCell align="right">Notification Type</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>Data 3</TableCell>
              <TableCell align="right">
                <FormControlLabel
                  control={<Switch checked={motionSensorsChecked} onChange={handleMotionSensorsChange} />}
                  label=""
                />
              </TableCell>
              <TableCell align="right">App</TableCell>
            </TableRow>
            {/* Additional rows for other sensors */}
          </TableBody>
        </Table>
      </TableContainer>

    </div>
  );
};

export default SHP;
