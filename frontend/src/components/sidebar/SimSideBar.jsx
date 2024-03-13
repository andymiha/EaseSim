import React, { useState } from 'react';
import { AppBar, Box, CssBaseline, Drawer, IconButton, Toolbar, Typography, Button, FormControl, NativeSelect, Slider, Dialog, DialogTitle, DialogContent, DialogActions } from '@mui/material';
import MenuIcon from "@mui/icons-material/Menu";
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import SimOnSwitch from '../sidebar/SimOnSwitch'; 
import EditIcon from '@mui/icons-material/Edit';
import EditForm from './EditForm'; // Import the EditForm component

const drawerWidth = 240;

const typographyStyle = {
    marginTop: theme => theme.spacing(2),
    marginBottom: theme => theme.spacing(1),
};

const temperature = 25; 

const SimSideBar = ({ openDrawer, handleDrawerToggle, handleDrawerClose }) => {
  const [showForm, setShowForm] = useState(false);
  const [openModal, setOpenModal] = useState(false); // State to control modal visibility

  const handleEditClick = () => {
    setOpenModal(true); // Open the modal when Edit button is clicked
  };

  const handleModalClose = () => {
    setOpenModal(false); // Close the modal
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="toggle drawer"
            onClick={handleDrawerToggle}
            edge="start"
            sx={{ mr: theme => theme.spacing(2) }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" noWrap component="div">
            Dashboard
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="persistent"
        anchor="left"
        open={openDrawer}
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': { width: drawerWidth, boxSizing: 'border-box' },
          marginTop: '64px',
        }}
      >
        <Toolbar>
          <IconButton onClick={handleDrawerClose}>
            {<ChevronLeftIcon />}
          </IconButton>
        </Toolbar>
        
        <Box sx={{ p: theme => theme.spacing(2) }}>
          <Typography variant="h6" gutterBottom >
            Simulation
          </Typography>
  
          <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
  
            <Typography paragraph sx={typographyStyle}>
              Turn on/off the simulation
            </Typography>
            <SimOnSwitch
              onChange={(event) => {
                if (event.target.checked) {
                  // Put backend behavior/function/endpoint here
                  console.log('Switch turned on. Backend action can be triggered here.');
                }
              }}
            />
         
            <Typography paragraph sx={typographyStyle}>
              Edit Context
            </Typography>
  
            {/* Edit Button */}
            <div>
              <IconButton color="primary" aria-label="Edit simulation context" onClick={handleEditClick}>
                <EditIcon />
              </IconButton>
            </div>
  
            <Typography paragraph sx={typographyStyle}>
              Current user type:
            </Typography>
  
            <Button
              variant="contained"
              color="primary"
              sx={{
                borderRadius: '50px', // Adjust the value for the desired curvature
                padding: '12px 24px', // Adjust the padding as needed
                marginTop: theme => theme.spacing(2),
                marginBottom: theme => theme.spacing(1),
              }}
            >
              Parent
            </Button>
  
            <Typography paragraph sx={typographyStyle}>
              Current Location
            </Typography>
  
            <FormControl fullWidth sx={typographyStyle}  >
              <NativeSelect
                defaultValue={30}
                inputProps={{
                  name: 'Location',
                  id: 'uncontrolled-native',
                }}
              >
                <option value="">Select Room</option>
                <option value={10}>Kitchen</option>
                <option value={20}>Living Room</option>
                <option value={30}>Bedroom 1</option>
                <option value={30}>Bedroom 2</option>
                <option value={30}>Bathroom</option>
              </NativeSelect>
            </FormControl>
  
            <Typography paragraph sx={typographyStyle}>
              Outside temperature
            </Typography>
  
            <Typography
              variant="h6"
              component="span"
              color="textPrimary"
              sx={{
                borderRadius: '50%',
                backgroundColor: '#7986cb', // Adjust the color as needed
                color:'white',
                padding: '12px', // Adjust the padding as needed
                display: 'inline-block', // Ensure the round background is applied
              }}
            >
              {temperature}&deg;C
            </Typography>
  
            {/*Date and time here*/}
            <Typography paragraph sx={typographyStyle}>
              Saturday March 9 <br/> 19:30:55
            </Typography>
  
            <Typography paragraph sx={typographyStyle}>
              Time speed
            </Typography>
  
            <Slider
              aria-label="Temperature"
              defaultValue={50}
              //getAriaValueText={valuetext}
              valueLabelDisplay="auto"
              shiftStep={20}
              step={10}
              marks
              min={10}
              max={100}
            />
  
          </div>
        </Box>
      </Drawer>
      
      {/* Modal for EditForm */}
      <Dialog open={openModal} onClose={handleModalClose}>
        <DialogTitle>Edit Context</DialogTitle>
        <DialogContent>
          <EditForm onClose={handleModalClose} />
        </DialogContent>
      </Dialog>
    </Box>
  );
};

export default SimSideBar;
