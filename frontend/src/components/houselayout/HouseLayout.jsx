import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack"

const HouseLayout = () => {

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
       {/* {isOn && (
          <div
            style={{
              position: "absolute",
              top: "50px", // Adjust position as needed
              left: "100px", // Adjust position as needed
              width: "50px", // Adjust size as needed
              height: "50px", // Adjust size as needed
              backgroundColor: "rgba(255, 0, 0, 0.5)", // Semi-transparent red color
            }}
          >
            {/* Add content for the overlay */}
          {/* </div>
          )} */} 
      </Stack>
    </Box>
  ); 
};

export default HouseLayout;


