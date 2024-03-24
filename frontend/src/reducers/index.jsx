const initialState = {
  windows: [{ id: 1, state: true }],
  doors: [{ id: 2, state: true }],
  lights: [{ id: 3, state: true }],
  userName: "Gilbert",
};

function rootReducer(state = initialState, action) {
  switch (action.type) {
    case 'CHANGE_NAME':
      return { ...state, userName: action.payload };
    case 'SET_WINDOWS':
        return { ...state, windows: action.payload };
    case 'ADD_WINDOW':
      return { ...state, windows: [...state.windows, action.payload] };
    case 'REMOVE_WINDOW':
      return { ...state, windows: state.windows.filter((_, index) => index !== action.payload) };
    case 'UPDATE_WINDOW_STATE':
      return {
        ...state,
        windows: state.windows.map((window, index) =>
          index === action.payload.index ? { ...window, state: action.payload.state } : window
        ),
      };

    case 'SET_DOORS':
        return { ...state, doors: action.payload };  
    case 'ADD_DOOR':
      return { ...state, doors: [...state.doors, action.payload] };
    case 'REMOVE_DOOR':
      return { ...state, doors: state.doors.filter((_, index) => index !== action.payload) };
    case 'UPDATE_DOOR_STATE':
      return {
        ...state,
        doors: state.doors.map((door, index) =>
          index === action.payload.index ? { ...door, state: action.payload.state } : door
        ),
      };
    
    case 'SET LIGHTS':
        return { ...state, lights: action.payload };
    case 'ADD_LIGHT':
      return { ...state, lights: [...state.lights, action.payload] };
    case 'REMOVE_LIGHT':
      return { ...state, lights: state.lights.filter((_, index) => index !== action.payload) };
    case 'UPDATE_LIGHT_STATE':
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
