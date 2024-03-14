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


const SHC = () => {
  const [lights, setLights] = useState([])
const [doors, setDoors] = useState([])
const [windows, setWindows] = useState([])

 // Fetch data from backend on mount
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

  console.log(lights);

   // Fetch data from backend on mount
 useEffect(() => {  
  fetch('http://localhost:8080/getHouseWindows') 
      .then(response => response.json())
      .then(data => {
         setWindows(data)
      })
      .then(console.log("bluetooth connect assucessfully"))
      .catch(error => console.error('Error fetching data:', error));
 },
  []);

  console.log(windows);

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
  
  //  console.log(doors);

  const [rows, setRows] = useState([]);

  const generateRows = (data) => {
    setRows(data.map((item) => ({
      room: item.roomName || item.roomFrom,
      roomTo: item.roomTo, 
      isOn: item.state,
      isBlocked: item.isBlocked || false,
      isAuto: item.isAuto || false,
    })));
  };

  const [alignment, setAlignment] = useState("");

  const handleAlignment = (event, newAlignment) => {
    setAlignment(newAlignment);
  };

  const handleSwitchChange = (index) => { 

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
        i === index ? {...row, isOn: !row.isOn} : row
      )
    );
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
              {alignment !== "center" && <TableCell>Room</TableCell>}
              {alignment === "center" && <TableCell>Rooms</TableCell>}
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
                  {alignment === "center" && (
                  <TableCell>{row.room} to {row.roomTo}</TableCell>
                   )}
                  {alignment !== "center" &&( <TableCell>{row.room}</TableCell>
                  )}
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

// export const Layout = () => {

//   return (
//     <Box>
//       <Stack
//          direction="column"
//          justifyContent="center"
//          alignItems="center"
//          spacing={5}
//       >
//       <Typography variant="h5" >House Layout</Typography>
//       <img
//         src="src/assets/HouseLayout.png"
//         alt="House Layout"
//         style={{ 
//         position: 'relative',
//         maxWidth: '100%', 
//         height: 'auto', 
//         }}
//       />
//         {isOn && (
//           <div
//             style={{
//               position: "absolute",
//               top: "50px", // Adjust position as needed
//               left: "100px", // Adjust position as needed
//               width: "50px", // Adjust size as needed
//               height: "50px", // Adjust size as needed
//               backgroundColor: "rgba(255, 0, 0, 0.5)", // Semi-transparent red color
//             }}
//           >
         
//            </div>
//           )}  
//       </Stack>
//     </Box>
//   ); 
 //