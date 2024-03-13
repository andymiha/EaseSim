import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack"

// const lights = [
//   { room: "Living", switchedOn: true, isAuto: true, dummy: "hi" },
//   { room: "Kitchen", switchedOn: false, isAuto: false, dummy: "hi" },
//   { room: "Bathroom", switchedOn: true, isAuto: false, dummy: "hi" },
//   { room: "BedroomOne", switchedOn: false, isAuto: false, dummy: "hi" },
//   { room: "BedroomTwo", switchedOn: true, isAuto: false, dummy: "hi" },
//   { room: "Entrance", switchedOn: false, isAuto: false, dummy: "hi" },
//   { room: "Backyard", switchedOn: false, isAuto: false, dummy: "hi" },
//   { room: "Garage", switchedOn: true, isAuto: false, dummy: "hi" },
//   { room: "Living", switchedOn: true, isAuto: false, dummy: "hi" },
//   { room: "Kitchen", switchedOn: false, isAuto: true, dummy: "hi" },
//   { room: "Bathroom", switchedOn: false, isAuto: false, dummy: "hi" },
//   { room: "BedroomOne", switchedOn: true, isAuto: false, dummy: "hi" },
//   { room: "BedroomTwo", switchedOn:true, isAuto: true, dummy: "hi" },
//   { room: "Entrance", switchedOn: true, isAuto: false, dummy: "hi" },
//   { room: "Backyard", switchedOn: false, isAuto: false, dummy: "hi" },
//   { room: "Garage", switchedOn: false, isAuto: false, dummy: "hi" },
// ];

// const doors = [
//   { room: "BedroomTwo", isOpen: false, isAuto: true, dummy: "hi" },
//   { room: "Entrance", isOpen: true, isAuto: false, dummy: "hi" },
//   { room: "Backyard", isOpen: false, isAuto: false, dummy: "hi" },
//   { room: "Garage", isOpen: false, isAuto: false, dummy: "hi" },
// ];

// const windows = [
//   { room: "Living", isOpen: false, isBlocked: false},
//   { room: "Kitchen", isOpen: true,  isBlocked: true },
//   { room: "Bathroom", isOpen: true,  isBlocked: false},
//   { room: "BedroomOne", isOpen: true,  isBlocked: false},
// ];


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


