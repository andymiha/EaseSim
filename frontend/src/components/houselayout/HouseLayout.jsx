import React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack"
import {useState, useEffect} from 'react';
import { useSelector, useDispatch} from 'react-redux';
import PersonIcon from '../../assets/Person.svg';
import SensorsIcon from '../../assets/Sensors.svg';
import DoorIcon from '../../assets/Door.svg';

const HouseLayout = ({ selectedRoom }) => {

  const [activeOverlays, setActiveOverlays] = useState([]);
  const windows = useSelector((state) => state.windows);
  const sensors = useSelector((state) => state.sensors);

// Define stick figure positions for each room
const stickFigurePositions = {
  "kitchen": { top: "20%", left: "30%", position: "absolute" },
  "living-room": { top: "40%", left: "30%", position: "absolute" },
  "bedroom-1": { top: "45%", left: "10%", position: "absolute" },
  "bedroom-2": { top: "20%", left: "10%", position: "absolute" },
  "bathroom": { top: "15%", left: "70%", position: "absolute" },
  "garage": { top: "40%", left: "70%", position: "absolute" },
  "backyard": { top: "2%", left: "40%", position: "absolute" },
  "frontyard": { top: "65%", left: "40%", position: "absolute" },
  // Add positions for other rooms as needed
};



  // Check if selectedRoom is defined and exists in stickFigurePositions
  const stickFigurePosition = selectedRoom && stickFigurePositions[selectedRoom];

  const updateOverlays = () => {
    const overlays = windows
      .filter((window) => window.state === true)
      .map((window) => window.id);
    setActiveOverlays(overlays);
  };

  // Call updateOverlays whenever windows array changes
  useEffect(() => {
    updateOverlays();
  }, [windows]);

  return (
    <div className="w-[900px] m-auto my-[100px]">
      <Box>
        <Stack
          direction="column"
          justifyContent="center"
          alignItems="center"
          spacing={5}
        >
        {/* <Typography variant="h5" >House Layout</Typography> */}
        <img
          className="absolute max-w-[800px] z-[-1]"
          src="src/assets/HouseLayoutBlank.jpeg"
          alt="House Layout"
        />


        <div className="flex flex-wrap p-9 z-[999] h-[400px]">
          {/* Bedroom 1 */}
          <div className="w-[154px] h-[154px] mt-[-16px] ml-[48px] p-4 border-2 border-black cursor-pointer hover:bg-gray-200/60">
            <h2 className="text-lg font-bold text-center">Bedroom 1</h2>

            {
              selectedRoom == 'bedroom-1' ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="bg-blue-100 rounded-full text-center text-black h-full">
                <img src={PersonIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }

            {
              sensors.bedroom1 ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="rounded-full text-center text-black h-full">
                <img src={SensorsIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }
          </div>

          {/* Kitchen */}
          <div className="w-[366px] p-4 h-[154px] border-2 mt-[-16px] border-black cursor-pointer hover:bg-gray-200/60">
            <h2 className="text-lg font-bold text-center">Kitchen</h2>

            {
              selectedRoom == 'kitchen' ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="bg-blue-100 rounded-full text-center text-black h-full">
                <img src={PersonIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }

            {
              sensors.kitchen ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="rounded-full text-center text-black h-full">
                <img src={SensorsIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }
          </div>

          <div className="w-[212px] p-4 h-[116px] border-2 mt-[-16px] border-black cursor-pointer hover:bg-gray-200/60">
            <h2 className="text-lg font-bold text-center">Bathroom</h2>

            {
              selectedRoom == 'bathroom' ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto mt-[-16px]"
            >
              <div className="bg-blue-100 rounded-full text-center text-black h-full">
                <img src={PersonIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }

            {
              sensors.bathroom ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="rounded-full text-center text-black h-full">
                <img src={SensorsIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }
          </div>

          <div className="w-[154px] h-[174px] ml-[48px] mt-[-16px] p-4 border-2 border-black cursor-pointer hover:bg-gray-200/60">
            <h2 className="text-lg font-bold text-center">Bedroom 2</h2>

            {
              selectedRoom == 'bedroom-2' ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="bg-blue-100 rounded-full text-center text-black h-full">
                <img src={PersonIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }

            {
              sensors.bedroom2 ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="rounded-full text-center text-black h-full">
                <img src={SensorsIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }
          </div>

          <div className="w-[366px] p-4 h-[172px] border-2 mt-[-16px] border-black cursor-pointer hover:bg-gray-200/60">
            <h2 className="text-lg font-bold text-center">Living Room</h2>

            {
              selectedRoom == 'living-room' ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto mt-4"
            >
              <div className="bg-blue-100 rounded-full text-center text-black h-full">
                <img src={PersonIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }

            {
              sensors.livingRoom ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="rounded-full text-center text-black h-full">
                <img src={SensorsIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }
          </div>

          <div className="w-[212px] h-[212px] p-4 border-2 mt-[-54px] border-black cursor-pointer hover:bg-gray-200/60">
            <h2 className="text-lg font-bold text-center">Garage</h2>

            {
              selectedRoom == 'garage' ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="bg-blue-100 rounded-full text-center text-black h-full">
                <img src={PersonIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }

            {
              sensors.garage ? 
            <div
              class="bg-gray-200/50 rounded-full h-[84px] w-[84px] p-2 m-auto"
            >
              <div className="rounded-full text-center text-black h-full">
                <img src={SensorsIcon} alt="Person" className="inline-block w-12 h-full" />
              </div>
            </div> : <div></div>
            }
          </div>
        </div>

        {/* {stickFigurePosition && (
          <img
            src="src/assets/Stick-Figure.png"
            alt="Stick Figure"
            style={{
              position: stickFigurePosition.position,
              top: stickFigurePosition.top,
              left: stickFigurePosition.left,
              maxWidth: '20%', 
              // Add any additional styling properties here
            }}
          />
        )} */}
        </Stack>
      </Box>
    </div>
  ); 
};

export default HouseLayout;


