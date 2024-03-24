import React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack"
import {useState, useEffect} from 'react';
import { useSelector, useDispatch} from 'react-redux';

const HouseLayout = ({ selectedRoom }) => {

  const [activeOverlays, setActiveOverlays] = useState([]);
  const windows = useSelector((state) => state.windows);

// Define stick figure positions for each room
const stickFigurePositions = {
  "kitchen": { top: "20%", left: "30%", position: "absolute" },
  "living-room": { top: "40%", left: "30%", position: "absolute" },
  "bedroom-1": { top: "45%", left: "10%", position: "absolute" },
  "bedroom-2": { top: "20%", left: "10%", position: "absolute" },
  "bathroom": { top: "15%", left: "70%", position: "absolute" },
  "garage": { top: "40%", left: "70%", position: "absolute" },
  "backyard": { top: "2%", left: "40%", position: "absolute" },
  "frontyard": { top: "65%", left: "40%", position: "absolute" },
  // Add positions for other rooms as needed
};



  // Check if selectedRoom is defined and exists in stickFigurePositions
  const stickFigurePosition = selectedRoom && stickFigurePositions[selectedRoom];

  const updateOverlays = () => {
    const overlays = windows
      .filter((window) => window.state === true)
      .map((window) => window.id);
    setActiveOverlays(overlays);
  };

  // Call updateOverlays whenever windows array changes
  useEffect(() => {
    updateOverlays();
  }, [windows]);

  return (
    <Box>
      <Stack
         direction="column"
         justifyContent="center"
         alignItems="center"
         spacing={5}
      >
      <Typography variant="h5" >House Layout</Typography>
      <img
        src="src/assets/HouseLayout.png"
        alt="House Layout"
        style={{ 
        position: 'relative',
        maxWidth: '100%', 
        height: 'auto', 
        }}
      />

      {/* Stick figure */}
      {stickFigurePosition && (
  <img
  src="src/assets/Stick-Figure.png"
    alt="Stick Figure"
    style={{
      position: stickFigurePosition.position,
      top: stickFigurePosition.top,
      left: stickFigurePosition.left,
      maxWidth: '20%', 
      // Add any additional styling properties here
    }}
  />
)}
{activeOverlays.map((overlayId) => (
          <div
            key={overlayId}
            style={{
              position: 'absolute',
              top: '0',
              left: '0',
              width: '100%',
              height: '100%',
              background: 'rgba(0, 0, 0, 0.5)', // Example overlay color
              zIndex: '100', // Ensure overlay is on top of other elements
            }}
          />
))}
      </Stack>
    </Box>
  ); 
};

export default HouseLayout;


