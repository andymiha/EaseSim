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
        
      </Stack>
    </Box>
  ); 
};

export default HouseLayout;


