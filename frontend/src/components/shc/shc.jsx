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
import Switch from "@mui/material/Switch";
import { useState, useEffect } from "react";
//working

//onMount fetch 
//method get in backend in controller 
//this method is quiered in fronend

//const [lights, setLights] = useState([])
//const [doors, setDoors] = useState([])
//const [windows, setWindows] = useState([])

const lights = [
  { room: "Living", isOn: true, isAuto: true, dummy: "hi" },
  { room: "Kitchen", isOn: false, isAuto: false, dummy: "hi" },
  { room: "Bathroom", isOn: true, isAuto: false, dummy: "hi" },
  { room: "BedroomOne", isOn: false, isAuto: false, dummy: "hi" },
  { room: "BedroomTwo", isOn: true, isAuto: false, dummy: "hi" },
  { room: "Entrance", isOn: false, isAuto: false, dummy: "hi" },
  { room: "Backyard", isOn: false, isAuto: false, dummy: "hi" },
  { room: "Garage", isOn: true, isAuto: false, dummy: "hi" },
  { room: "Living", isOn: true, isAuto: false, dummy: "hi" },
  { room: "Kitchen", isOn: false, isAuto: true, dummy: "hi" },
  { room: "Bathroom", isOn: false, isAuto: false, dummy: "hi" },
  { room: "BedroomOne", isOn: true, isAuto: false, dummy: "hi" },
  { room: "BedroomTwo", isOn:true, isAuto: true, dummy: "hi" },
  { room: "Entrance", isOn: true, isAuto: false, dummy: "hi" },
  { room: "Backyard", isOn: false, isAuto: false, dummy: "hi" },
  { room: "Garage", isOn: false, isAuto: false, dummy: "hi" },
];

const doors = [
  { room: "BedroomTwo", isOn: false, isAuto: true, dummy: "hi" },
  { room: "Entrance", isOn: true, isAuto: false, dummy: "hi" },
  { room: "Backyard", isOn: false, isAuto: false, dummy: "hi" },
  { room: "Garage", isOn: false, isAuto: false, dummy: "hi" },
];

const windows = [
  { room: "Living", isOn: false, isBlocked: false, dummy: "hi" },
  { room: "Kitchen", isOn: true,  isBlocked: true, dummy: "hi" },
  { room: "Bathroom", isOn: true,  isBlocked: false, dummy: "hi" },
  { room: "BedroomOne", isOn: true,  isBlocked: false, dummy: "hi" },
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

  const handleSwitchChange = (index) => { 
    setRows ((prevRows) => 
      prevRows.map((row, i) =>
        i === index ? {...row, isOn: !row.isOn} : row
      )
    );
  };

  useEffect(() => {
    console.log("Rows updated:", rows);
  }, [rows]);


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
                <TableCell>Room</TableCell>
                <TableCell>On/Off</TableCell>
                <TableCell>Auto-mode</TableCell>
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
                  <Switch
                    checked={row.isOn}
                    onChange = {() => handleSwitchChange (index)}
                    />
                    {row.isOn}</TableCell>
                  <TableCell>
          
                    {row.isAuto}</TableCell>
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

