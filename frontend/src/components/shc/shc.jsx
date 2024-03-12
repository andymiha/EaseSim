import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";
import { useState } from "react";

const lights = [
  { room: "Living", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "Kitchen", isOn: "off", isAuto: "off", dummy: "hi" },
  { room: "Bathroom", isOn: "off", isAuto: "on", dummy: "hi" },
  { room: "BedroomOne", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "BedroomTwo", isOn: "on", isAuto: "off", dummy: "hi" },
  { room: "Entrance", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "Backyard", isOn: "off", isAuto: "off", dummy: "hi" },
  { room: "Garage", isOn: "off", isAuto: "on", dummy: "hi" },
  { room: "Living", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "Kitchen", isOn: "off", isAuto: "off", dummy: "hi" },
  { room: "Bathroom", isOn: "off", isAuto: "on", dummy: "hi" },
  { room: "BedroomOne", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "BedroomTwo", isOn: "on", isAuto: "off", dummy: "hi" },
  { room: "Entrance", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "Backyard", isOn: "off", isAuto: "off", dummy: "hi" },
  { room: "Garage", isOn: "off", isAuto: "on", dummy: "hi" },
];

const doors = [
  { room: "BedroomTwo", isOn: "on", isAuto: "off", dummy: "hi" },
  { room: "Entrance", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "Backyard", isOn: "off", isAuto: "off", dummy: "hi" },
  { room: "Garage", isOn: "off", isAuto: "on", dummy: "hi" },
];

const windows = [
  { room: "Living", isOn: "on", isAuto: "on", dummy: "hi" },
  { room: "Kitchen", isOn: "off", isAuto: "off", dummy: "hi" },
  { room: "Bathroom", isOn: "off", isAuto: "on", dummy: "hi" },
  { room: "BedroomOne", isOn: "on", isAuto: "on", dummy: "hi" },
];

const columns = [
  { field: "room", headerName: "Room", width: 250 },
  { field: "isOn", headerName: "On/Off", width: 100 },
  { field: "isAuto", headerName: "Auto-Mode", width: 100 },
];

const ItemList = () => {
  const [rows, setRows] = useState([]);

  // Function to generate rows
  const generateRows = (data) => {
    setRows(
      data.map((item) => ({
        room: item.room,
        isOn: item.isOn,
        isAuto: item.isAuto,
      }))
    );
  };

  const [alignment, setAlignment] = useState("");

  const handleAlignment = (event, newAlignment) => {
    setAlignment(newAlignment);
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
          <Table stickyHeader sx={{ minWidth: 650 , maxHeight: 440}} >
            <TableHead>
              <TableRow>
                <TableCell>Room</TableCell>
                <TableCell>On/Off</TableCell>
                <TableCell>Auto-mode</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                <TableRow
                  key={row.name}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell>{row.room}</TableCell>
                  <TableCell>{row.isOn}</TableCell>
                  <TableCell>{row.isAuto}</TableCell>
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

export default ItemList;
