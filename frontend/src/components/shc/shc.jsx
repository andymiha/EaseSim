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
import Checkbox from "@mui/material/Checkbox";
import { useState, useEffect } from "react";
//working

//onMount fetch 
//method get in backend in controller 
//this method is quiered in fronend

//const [lights, setLights] = useState([])
//const [doors, setDoors] = useState([])
//const [windows, setWindows] = useState([])

const lights = [
  { room: "Living", switchedOn: true, isAuto: true, dummy: "hi" },
  { room: "Kitchen", switchedOn: false, isAuto: false, dummy: "hi" },
  { room: "Bathroom", switchedOn: true, isAuto: false, dummy: "hi" },
  { room: "BedroomOne", switchedOn: false, isAuto: false, dummy: "hi" },
  { room: "BedroomTwo", switchedOn: true, isAuto: false, dummy: "hi" },
  { room: "Entrance", switchedOn: false, isAuto: false, dummy: "hi" },
  { room: "Backyard", switchedOn: false, isAuto: false, dummy: "hi" },
  { room: "Garage", switchedOn: true, isAuto: false, dummy: "hi" },
  { room: "Living", switchedOn: true, isAuto: false, dummy: "hi" },
  { room: "Kitchen", switchedOn: false, isAuto: true, dummy: "hi" },
  { room: "Bathroom", switchedOn: false, isAuto: false, dummy: "hi" },
  { room: "BedroomOne", switchedOn: true, isAuto: false, dummy: "hi" },
  { room: "BedroomTwo", switchedOn:true, isAuto: true, dummy: "hi" },
  { room: "Entrance", switchedOn: true, isAuto: false, dummy: "hi" },
  { room: "Backyard", switchedOn: false, isAuto: false, dummy: "hi" },
  { room: "Garage", switchedOn: false, isAuto: false, dummy: "hi" },
];

const doors = [
  { room: "BedroomTwo", isOpen: false, isAuto: true, dummy: "hi" },
  { room: "Entrance", isOpen: true, isAuto: false, dummy: "hi" },
  { room: "Backyard", isOpen: false, isAuto: false, dummy: "hi" },
  { room: "Garage", isOpen: false, isAuto: false, dummy: "hi" },
];

const windows = [
  { room: "Living", isOpen: false, isBlocked: false},
  { room: "Kitchen", isOpen: true,  isBlocked: true },
  { room: "Bathroom", isOpen: true,  isBlocked: false},
  { room: "BedroomOne", isOpen: true,  isBlocked: false},
];

const SHC = () => {
  const [rows, setRows] = useState([]);

  const generateRows = (data) => {
    setRows(data.map((item) => ({
      room: item.room,
      isOn: item.switchedOn || item.isOpen, // Combined isOpen and switchedOn depending on the data source
      isBlocked: item.isBlocked || false,
      isAuto: item.isAuto || false,
    })));
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

  const handleCheckboxChange = (index) => { 
    setRows ((prevRows) => 
      prevRows.map((row, i) =>
        i === index ? {...row, isAuto: !row.isAuto} : row
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
                  <Switch
                    checked={row.isOn}
                    onChange = {() => handleSwitchChange (index)}
                    disabled = {row.isBlocked || row.isAuto}
                    />
                    {row.isOn}</TableCell>
                    {alignment !== "right" && (
                  <TableCell>
                  <Checkbox
                        checked={row.isAuto}
                        onChange = {() => handleCheckboxChange (index)}
                      />
                    {row.isAuto}</TableCell>
                      )}
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

