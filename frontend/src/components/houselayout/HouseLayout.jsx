import React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack"

const HouseLayout = ({ selectedRoom, windowStates }) => {

// Stick figure positions for each room
const stickFigurePositions = {
  "kitchen": { top: "20%", left: "30%", position: "absolute" },
  "living-room": { top: "40%", left: "30%", position: "absolute" },
  "bedroom-1": { top: "45%", left: "10%", position: "absolute" },
  "bedroom-2": { top: "20%", left: "10%", position: "absolute" },
  "bathroom": { top: "15%", left: "70%", position: "absolute" },
  "garage": { top: "40%", left: "70%", position: "absolute" },
  "backyard": { top: "2%", left: "40%", position: "absolute" },
  "frontyard": { top: "65%", left: "40%", position: "absolute" },

};

// Stick figure positions for each room
const WindowPositions = {
  "2": { top: "60%", left: "2%", position: "absolute" },
  "6": { top: "40%", left: "2%", position: "absolute" },
  "10": { top: "25%", left: "32%", position: "absolute" },
  "11": { top: "25%", left: "57%", position: "absolute" },
  "17": { top: "75%", left: "32%", position: "absolute" },
  "23": { top: "35%", left: "90%", position: "absolute" },
  "26": { top: "60%", left: "90%", position: "absolute" },
};


// Overlay text for blocked windows
const overlayTexts = Object.entries(windowStates)
.filter(([windowId, isBlocked]) => isBlocked) // Filter out only the blocked windows
.map(([windowId, isBlocked]) => {
  const position = WindowPositions[windowId]; // Get position for the blocked window
  return (


    <img
    key={windowId}
    src="src/assets/bigrock.png"
    alt="BIG ROCK"
    style={{
      position: 'absolute',
        top: position.top,
        left: position.left,
        maxWidth: '5%', 
        zIndex: 1000, // Ensure text appears above the imagee
    }}
  />
  );
});

  // Check if selectedRoom is defined and exists in stickFigurePositions
  const stickFigurePosition = selectedRoom && stickFigurePositions[selectedRoom];

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

  {/* Overlay text for blocked windows */}
    {overlayTexts}
      </Stack>
    </Box>
  ); 
};

export default HouseLayout;


