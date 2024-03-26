import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import SimSideBar from '../components/sidebar/SimSideBar';
import { Grid, Paper, Tab, Tabs, Drawer, Typography } from '@mui/material';
import HouseLayout from '../components/houselayout/HouseLayout'; 
import SHS from '../components/shs/shs';
import SHC from '../components/shc/shc'; 
import SHP from '../components/shp/shp';
import SHH from '../components/shh/shh';
import { useDispatch } from 'react-redux';
import WindowOpenIcon from '../assets/Window-Open.svg';
import LightIcon from '../assets/Light.svg';
import DoorIcon from '../assets/Door.svg';

const Dashboard = () => {
  const [openDrawer, setOpenDrawer] = useState(true);
  const [selectedTab, setSelectedTab] = useState(0);
  const [selectedRoom, setSelectedRoom] = useState(''); // State to store selected room ID
  const dispatch = useDispatch();

  //dummy data
  const lights = [
    { id: 1, name: 'Living Room Light', state: true, isAuto: false},
    { id: 2, name: 'Bedroom Light', state: true, isAuto: false},
    { id: 3, name: 'Kitchen Light', state: false, isAuto: false },
  ];
  const windows = [
    { id: 1, name: 'Living Room window', state: true },
    { id: 2, name: 'Bedroom window', state: false},
    { id: 3, name: 'Kitchen window', state: true},
  ];
  const doors = [
    { id: 1, name: 'Living Room door', state: false, isAuto: false },
    { id: 2, name: 'Bedroom door', state: false, isAuto: false},
    { id: 3, name: 'Kitchen door', state: true, isAuto: false },
  ];

  useEffect(() => {
    fetch('http://localhost:8080/getHouseElements') 
        .then(response => response.json())
        .then(data => {
          console.log(data.doors)
          dispatch({ type: 'SET_LIGHTS', payload: data.lights });
          dispatch({ type: 'SET_WINDOWS', payload: data.windows });
          dispatch({ type: 'SET_DOORS', payload: data.doors });
        })
        .then(console.log("Grabbed elements sucessfully"))
        .catch(error => console.error('Error fetching data:', error));

      fetch('http://localhost:8080/getData') 
        .then(response => response.json())
        .then(data => {
          console.log(data);
          dispatch({ type: 'SET_ROOMS', payload: data.rooms });
        })
        .then(console.log("Grabbed rooms sucessfully"))
        .catch(error => console.error('Error fetching data:', error));
  }, []);

  //set dispatches for initial page render 
  useEffect(() => {
    dispatch({ type: 'SET_WINDOWS', payload: windows });
    dispatch({ type: 'SET_DOORS', payload: doors });
    dispatch({ type: 'SET_LIGHTS', payload: lights });
  }, []);

  //set dispatches for any changes in doors, windows, lights
  // useEffect(() => {
  //   dispatch({ type: 'UPDATE_WINDOWS_OPEN_STATE', payload: windows });
  //   dispatch({ type: 'UPDATE_WINDOWS_BLOCKED_STATE', payload: windows });
  //   dispatch({ type: 'UPDATE_DOORS_OPEN_STATE', payload: doors });
  //   dispatch({ type: 'UPDATE_DOORS_AUTO_STATE', payload: doors });
  //   dispatch({ type: 'UPDATE_LIGHTS_OPEN_STATE', payload: lights });
  //   dispatch({ type: 'UPDATE_LIGHTS_AUTO_STATE', payload: lights });

  // }, [lights, doors, windows, dispatch]);


  //create constant element states
  const userName = useSelector(state => state.userName);
  const globalWindows = useSelector(state => state.windows);
  const globalDoors = useSelector(state => state.doors);
  const globalLights = useSelector(state => state.lights);
  const globalRooms = useSelector(state => state.rooms);

  const doorConnections = globalRooms.reduce((acc, room) => {
    room.doors.forEach(door => {
      // Find the entry for the door
      let existingEntry = acc.find(entry => entry.doorId === door.id);
  
      if (existingEntry) {
        // Add the room to the connections if it's not already listed and there are less than 2 connections
        if (!existingEntry.connections.includes(room.name) && existingEntry.connections.length < 2) {
          existingEntry.connections.push(room.name);
        }
      } else {
        // Create a new entry for the door
        acc.push({ doorId: door.id, connections: [room.name] });
      }
    });
    return acc;
  }, []).map(entry => ({
    doorId: entry.doorId,
    // Concatenate the room names
    connections: entry.connections.length === 1 ? entry.connections[0] + ", Outside" : entry.connections.join(', ')
  }));
  
  console.log(doorConnections);  

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

  const tabComponents = {
    0: <SHS />,
    1: <SHC />,
    2: <SHP/>,
    3: <SHH/>,
  };

  return (
    <div className="my-6 flex">
      <div className="border-solid border-r-2 border-r-[#ededed] mr-6">
        <SimSideBar
          openDrawer={openDrawer}
          handleDrawerToggle={handleDrawerToggle}
          handleDrawerClose={handleDrawerClose}
          handleRoomChange={handleRoomChange} // Pass the setSelectedRoom function down as a prop
        />
      </div>

      <div className="grid grid-cols-2 gap-4 w-full mr-6">
        <div className="text-4xl font-bold col-span-2 w-full border-b-solid border-b-2 py-6">
          Welcome, {userName}
        </div>

        <div className="col-span-2">
          <div className="text-4xl font-bold my-4">
            Rooms
          </div>
          <div className="flex overflow-x-auto">
          {globalRooms.map((item, index) => {
            // Check if the room is FrontYard or BackYard and exclude it
              if (item.name === "FrontYard" || item.name === "BackYard") {
                return null; // Skip this iteration and don't render anything
              }
            return (
            <div className="w-auto border-2 border-[#ededed] rounded-2xl p-4 mr-4">
              <div className="text-2xl font-bold whitespace-nowrap mb-4">
                {item.name}
              </div>
              <div className="flex">
                {globalLights.filter(lightObj => lightObj.roomName === item.name).map((light, lightIndex) => (
                  <div className="flex mr-4" key={lightIndex}>
                    <div className="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                      <div className={`${light.state ? "bg-yellow-100" : "bg-gray-100"} rounded-full text-center text-black h-full`}>
                        <img src={LightIcon} alt="Light" className="inline-block w-10 h-full" />
                      </div>
                    </div>

                    <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                      Light {/* Assuming you have a name property in your light objects */}

                      <div className="whitespace-nowrap opacity-50">
                        {light.state ? "On" : "Off"}
                      </div>
                    </div>
                  </div>
                ))}

                {globalWindows.filter(windowObj => windowObj.roomName === item.name).map((window, windowIndex) => (
                  <div className="flex mr-4" key={windowIndex}>
                    <div className="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                      <div className={`${window.state ? "bg-green-100" : "bg-gray-100"} rounded-full text-center text-black h-full`}>
                        <img src={WindowOpenIcon} alt="Window" className="inline-block w-10 h-full" />
                      </div>
                    </div>

                    <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                      Window {/* Assuming you have a name property in your light objects */}

                      <div className="whitespace-nowrap opacity-50">
                        {window.state ? "On" : "Off"}
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )})}
          </div>
        </div>

        <div className="col-span-2">
          <div className="text-4xl font-bold my-4">
            Doors
          </div>

          <div className="flex overflow-x-auto ml-4">
            <div className="flex mr-4">
              <div class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                <div className="bg-green-100 rounded-full text-center text-black h-full">
                  <img src={DoorIcon} alt="Light" className="inline-block w-10 h-full" />
                </div>
              </div>

              <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                Bedroom 1, Kitchen

                <div className="whitespace-nowrap opacity-50">
                  Open
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="col-span-2 m-auto w-full">
          <div className="text-4xl font-bold mt-4 mb-[-48px] z-[9]">
            House Layout
          </div>
          <HouseLayout selectedRoom={selectedRoom} />
        </div>

        <div className="col-span-2 border-solid border-2 border-[#ededed] rounded-2xl p-6">
          <Tabs
              value={selectedTab}
              onChange={handleTabChange}
              indicatorColor="primary"
              textColor="primary"
              className="z-[999]"
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
        </div>

        <div className="col-span-2 border-solid border-2 border-[#ededed] rounded-2xl p-6">
          Logs
          {JSON.stringify(globalLights)}
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
