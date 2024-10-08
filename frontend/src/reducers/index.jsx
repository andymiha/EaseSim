const initialState = {
  windows: [],
  doors: [],
  lights: [],
  rooms: [],
  logs: [],
  sensors: {
    bedroom1: false,
    bedroom2: false,
    kitchen: false,
    bathroom: false,
    livingRoom: false,
    garage: false
  },
  userName: "Gian-Carlo",
};

function rootReducer(state = initialState, action) {
  switch (action.type) {
    case 'ADD_LOG':
      const newLog = action.payload;
      return {
        ...state,
        logs: [...state.logs, newLog],
      };
    case 'UPDATE_SENSOR':
      const { sensorName, isChecked } = action.payload;
      return {
        ...state,
        sensors: {
          ...state.sensors,
          [sensorName]: isChecked,
        },
      };
    case 'SET_ROOMS':
        return { ...state, rooms: action.payload };
    //window actions
    case 'SET_WINDOWS':
        return { ...state, windows: action.payload };
    case 'UPDATE_WINDOW_OPEN_STATE':
      return {
        ...state,
        windows: state.windows.map(window =>
          window.id === action.payload.id ? { ...window, state: action.payload.state } : window
        ),
      };
    case 'UPDATE_WINDOW_BLOCKED_STATE':
        return state.map(window =>
            window.id === action.payload.id
              ? { ...window, state: action.payload.state }
              : window
          );
   

   

    //door actiona
    case 'SET_DOORS':
        return { ...state, doors: action.payload };  
    case 'UPDATE_DOOR_OPEN_STATE':
      return {
        ...state,
        doors: state.doors.map((door, index) =>
          index === action.payload.index ? { ...door, state: action.payload.state } : door
        ),
      };
    case 'UPDATE_DOOR_AUTO_STATE':
        return {
          ...state,
          lights: state.lights.map((light, index) =>
            index === action.payload.index ? { ...light, state: action.payload.state } : light
          ),
        };

    //light actions
    case 'SET_LIGHTS':
        return { ...state, lights: action.payload };
    case 'UPDATE_LIGHT_ON_STATE':
      return {
        ...state,
        lights: state.lights.map(light =>
          light.id === action.payload.id ? { ...light, state: action.payload.state } : light
        ),
      };
    case 'UPDATE_LIGHT_AUTO_STATE':
        return {
          ...state,
          lights: state.lights.map((light, index) =>
            index === action.payload.index ? { ...light, state: action.payload.state } : light
          ),
        };
    default:
      return state;
  }
}

export default rootReducer;
