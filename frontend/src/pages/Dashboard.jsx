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

  //set dispatches for initial page render 
  useEffect(() => {
  
    dispatch({ type: 'SET_WINDOWS', payload: windows });
    dispatch({ type: 'SET_DOORS', payload: doors });
    dispatch({ type: 'SET_LIGHTS', payload: lights });

  }, []);

  //set dispatches for any changes in doors, windows, lights
  useEffect(() => {
  
    dispatch({ type: 'UPDATE_WINDOWS_OPEN_STATE', payload: windows });
    dispatch({ type: 'UPDATE_WINDOWS_BLOCKED_STATE', payload: windows });
    dispatch({ type: 'UPDATE_DOORS_OPEN_STATE', payload: doors });
    dispatch({ type: 'UPDATE_DOORS_AUTO_STATE', payload: doors });
    dispatch({ type: 'UPDATE_LIGHTS_OPEN_STATE', payload: lights });
    dispatch({ type: 'UPDATE_LIGHTS_AUTO_STATE', payload: lights });

  }, [lights, doors, windows, dispatch]);


  //create constant element states
  const userName = useSelector(state => state.userName);
  const globalWindows = JSON.stringify(useSelector(state => state.windows));
  const globalDoors = JSON.stringify(useSelector(state => state.doors));
  const globalLights = JSON.stringify(useSelector(state => state.lights));

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
            <div className="w-auto border-2 border-[#ededed] rounded-2xl p-4">
              <div className="text-2xl font-bold whitespace-nowrap mb-4">
                Bedroom 1
              </div>

              <div className="flex">
                <div className="flex mr-4">
                  <div class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                    <div className="bg-yellow-100 rounded-full text-center text-black h-full">
                      <img src={LightIcon} alt="Light" className="inline-block w-10 h-full" />
                    </div>
                  </div>

                  <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                    Light 1

                    <div className="whitespace-nowrap opacity-50">
                      On
                    </div>
                  </div>
                </div>

                <div className="flex">
                  <div class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                    <div className="bg-gray-100 rounded-full text-center text-black h-full">
                      <img src={LightIcon} alt="Light" className="inline-block w-10 h-full" />
                    </div>
                  </div>

                  <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                    Light 2

                    <div className="whitespace-nowrap opacity-50">
                      Off
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div className="min-w-[480px] border-2 border-[#ededed] rounded-2xl p-4 ml-4">
              <div className="text-2xl font-bold mb-4">
                Kitchen
              </div>

              <div className="flex">
                <div className="flex mr-4">
                  <div class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                    <div className="bg-gray-100 rounded-full text-center text-black h-full">
                      <img src={WindowOpenIcon} alt="Light" className="inline-block w-10 h-full" />
                    </div>
                  </div>

                  <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                    Window 1

                    <div className="whitespace-nowrap opacity-50">
                      Closed
                    </div>
                  </div>
                </div>

                <div className="flex">
                  <div class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2">
                    <div className="bg-green-100 rounded-full text-center text-black h-full">
                      <img src={WindowOpenIcon} alt="Light" className="inline-block w-10 h-full" />
                    </div>
                  </div>

                  <div className="items-center mt-4 ml-4 font-bold whitespace-nowrap">
                    Window 2

                    <div className="whitespace-nowrap opacity-50">
                      Open
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div className="min-w-[480px] border-2 border-[#ededed] rounded-2xl p-4 ml-4">
              <div className="text-2xl font-bold">
                Bathroom
              </div>
            </div>
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
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
