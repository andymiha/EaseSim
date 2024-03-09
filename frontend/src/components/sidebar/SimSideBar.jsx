import React from 'react';
import { AppBar, Box, CssBaseline, Drawer, IconButton, Toolbar, Typography } from '@mui/material';
import MenuIcon from "@mui/icons-material/Menu";
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import SimOnSwitch from '../sidebar/SimOnSwitch'; 

const drawerWidth = 240;

const SimSideBar = ({ openDrawer, handleDrawerToggle, handleDrawerClose }) => {
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
          marginTop: '64px', // Adjust the marginTop value
        }}
      >
        <Toolbar>
          <IconButton onClick={handleDrawerClose}>
            {<ChevronLeftIcon />}
          </IconButton>
        </Toolbar>
        <Box sx={{ p: theme => theme.spacing(2) }}>
          <Typography variant="h6" gutterBottom>
            Drawer Content
          </Typography>
          <Typography paragraph>
            Your text content goes here.
          </Typography>
          {/* Replace the Button with SimOnSwitch */}
          <SimOnSwitch
            onChange={(event) => {
              if (event.target.checked) {
                // Put backend behavior/function/endpoint here
                console.log('Switch turned on. Backend action can be triggered here.');
              }
            }}
          />
        </Box>
      </Drawer>
    </Box>
  );
};

export default SimSideBar;
