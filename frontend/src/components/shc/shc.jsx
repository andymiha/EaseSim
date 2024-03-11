// import React from 'react';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import ItemList from '/Users/noura/Desktop/EaseSim/frontend/src/components/shc/ItemList.jsx'; 




const SHC = () => {
  // Add your house layout content here
  return (
    <Box sx={{ width: 1 }}>
    <Stack   
      direction="column"
      justifyContent="space-evenly"
      alignItems= "center"
      spacing={2}>
      <ButtonGroup variant="outlined">
        <Button>Lights</Button>
        <Button>Doors</Button>
        <Button>Windows</Button>
      </ButtonGroup>
      <ItemList/>
    </Stack>
  </Box>
  );
};

export default SHC;