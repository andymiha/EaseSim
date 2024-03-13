import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";

const HouseLayout = () => {
  // const [lightStates, setLightStates] = useState({
  //   kitchenLight: false,
  //   livingRoomLight: false,
  //   // Add more lights as needed
  // });

  // const [overlayPosition, setOverlayPosition] = useState({ top: 0, left: 0 });

  // const handleLightButtonClick = (lightName) => {
  //   // Perform the action to open/close the specific light
  //   setLightStates((prevState) => ({
  //     ...prevState,
  //     [lightName]: !prevState[lightName],
  //   }));

  //   // Update the overlay position based on the light's location
  //   // This is a placeholder, adjust with actual coordinates for each light
  //   setOverlayPosition({ top: 100, left: 200 });
  // };

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
        maxWidth: '100%', // Ensure the image fits its container
        height: 'auto', // Maintain aspect rati }}
        }}
      />
      {/* {Object.entries(lightStates).map(([lightName, isLightOn]) => (
        isLightOn && (
          <div
            key={lightName}
            className="translucent-circle"
            style={{
              position: 'absolute',
              top: overlayPosition.top,
              left: overlayPosition.left,
              width: '50px',
              height: '50px',
              borderRadius: '50%',
              backgroundColor: 'rgba(255, 255, 0, 0.5)', // Translucent yellow
              pointerEvents: 'none', // Allow clicks to pass through the overlay
            }}
          ></div>
        )
      ))} */}
      {/* <button onClick={() => handleLightButtonClick('kitchenLight')}>Toggle Kitchen Light</button>
      <button onClick={() => handleLightButtonClick('livingRoomLight')}>Toggle Living Room Light</button>
      {/* Add more buttons for other lights as needed */}
      </Stack>
    </Box>
  ); 
};

export default HouseLayout;


