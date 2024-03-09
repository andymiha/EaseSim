import React, { useState } from 'react';
import SimSideBar from '../components/sidebar/SimSideBar';
import { Grid, Paper, Tab, Tabs, Typography } from '@mui/material';
import Toolbar from '@mui/material/Toolbar';

const Dashboard = () => {
  const [openDrawer, setOpenDrawer] = useState(false);
  const [selectedTab, setSelectedTab] = useState(0);

  const handleDrawerToggle = () => {
    setOpenDrawer(!openDrawer);
  };

  const handleDrawerClose = () => {
    setOpenDrawer(false);
  };

  const handleTabChange = (event, newValue) => {
    setSelectedTab(newValue);
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
        />
        
      <Grid item xs={12} sm={openDrawer ? 5 : 6}>

        <Paper
          component="div"
          elevation={3}
          sx={{
            position: 'absolute',
            maxWidth:'50%',
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

          <Typography paragraph>
            {selectedTab === 0 && 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'}
            {selectedTab === 1 && 'SHC Content'}
            {selectedTab === 2 && 'SHP Content'}
            {selectedTab === 3 && 'SHH Content'}
          </Typography>
        </Paper>
      </Grid>

      <Grid item xs={12} sm={openDrawer ? 5 : 6}>

        <Paper
            elevation={3}
          component="div"
          sx={{
            position: 'absolute',
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
          {/* Add content for the right Paper here */}
          <Typography paragraph>
          Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
          </Typography>
        </Paper>
      </Grid>
      
    </Grid>
  );
};

export default Dashboard;
