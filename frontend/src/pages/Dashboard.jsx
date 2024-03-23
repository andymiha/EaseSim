import React, { useState } from 'react';
import SimSideBar from '../components/sidebar/SimSideBar';
import { Grid, Paper, Tab, Tabs, Typography } from '@mui/material';
import HouseLayout from '../components/houselayout/HouseLayout'; 
import SHS from '../components/shs/shs'; 
import SHC from '../components/shc/shc'; 
import SHP from '../components/shp/shp';
import SHH from '../components/shh/shh';
import EditForm from '../components/sidebar/EditForm'; // Import the EditForm component

const Dashboard = () => {
  const [openDrawer, setOpenDrawer] = useState(false);
  const [selectedTab, setSelectedTab] = useState(0);
  const [selectedRoom, setSelectedRoom] = useState(''); // State to store selected room ID
  const [windowStates, setWindowStates] = useState({}); // State to hold window states

  const handleDrawerToggle = () => {
    setOpenDrawer(!openDrawer);
  };

  const handleDrawerClose = () => {
    setOpenDrawer(false);
  };

  const handleTabChange = (event, newValue) => {
    setSelectedTab(newValue);
  };

  const handleRoomChange = (roomId) => {
    setSelectedRoom(roomId);
  };

      // Function to update window states
  const updateWindowStates = (windowId, isBlocked) => {
    setWindowStates(prevStates => ({
      ...prevStates,
        [windowId]: isBlocked
        }));
    };

  const tabComponents = {
    0: <SHS />,
    1: <SHC />,
    2: <SHP/>,
    3: <SHH/>,
  };

  return (
    <Grid container >
      <Grid item xs={openDrawer ? 2 : false} sm={2}>
        {/* Empty Grid item for adjusting layout */}
      </Grid>

      <SimSideBar
        openDrawer={openDrawer}
        handleDrawerToggle={handleDrawerToggle}
        handleDrawerClose={handleDrawerClose}
        handleRoomChange={handleRoomChange} // Pass the setSelectedRoom function down as a prop
        windowStates={windowStates} // Pass windowStates to SimSideBar
        updateWindowStates={updateWindowStates} // Pass updateWindowStates to SimSideBar
      />
        
      <Grid item xs={12} sm={openDrawer ? 5 : 6}>
        <Paper
          component="div"
          elevation={3}
          sx={{
            position: 'absolute',
            maxWidth:'59%',
            height: '70%',
            top: '10%',
            left: openDrawer ? '240px' : '0',
            right: openDrawer ? '50%' : '0',
            bottom: 0,
            overflow: 'auto',
            padding: '16px',
            marginLeft: '1%',
            transition: 'left 225ms cubic-bezier(0.4, 0, 0.6, 1) 0ms, right 225ms cubic-bezier(0.4, 0, 0.6, 1) 0ms',
          }}
        >
          <Tabs
            value={selectedTab}
            onChange={handleTabChange}
            indicatorColor="primary"
            textColor="primary"
            sx={{
              width: '100%',
              borderBottom: '1px solid #ccc',
              marginBottom: '16px',
            }}
          >
            <Tab label="SHS" />
            <Tab label="SHC" />
            <Tab label="SHP" />
            <Tab label="SHH" />
          </Tabs>

          {tabComponents[selectedTab]}

        </Paper>
      </Grid>

      <Grid item xs={12} sm={openDrawer ? 5 : 6}>
        <Paper
          elevation={3}
          component="div"
          sx={{
            position: 'absolute',
            height: '70%',
            top: '10%',
            left: openDrawer ? '50%' : '60%',
            right: 0,
            bottom: 0,
            overflow: 'auto',
            padding: '16px',
            marginLeft: '1%',
            marginRight: '1%',
            transition: 'left 225ms cubic-bezier(0.4, 0, 0.6, 1) 0ms, right 225ms cubic-bezier(0.4, 0, 0.6, 1) 0ms',
          }}
        >
          <HouseLayout selectedRoom={selectedRoom} windowStates={windowStates}/>
        </Paper>
      </Grid>


      <Grid item lg={10} md={12} sm={openDrawer ? 5 : 6} sx={{ position: 'absolute', width: '100%', height:'20%', bottom:'0', left: openDrawer ? '240px' : '0' }}>
        <Paper  
          elevation={3}
          component="div"
          sx={{
            overflow: 'auto',
            padding: '16px',
            Height:'100%',
            maxWidth:'90%',
            height: '100%', 
            margin:'1%',
            transition: 'left 225ms cubic-bezier(0.4, 0, 0.6, 1) 0ms, right 225ms cubic-bezier(0.4, 0, 0.6, 1) 0ms',
          }}
        >
          <Typography paragraph>
            Your content for the bottom Paper.
          </Typography>
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Dashboard;
