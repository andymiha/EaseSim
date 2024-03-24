const initialState = {
  windows: [{ id: 1, state: true, isBlocked: true}],
  doors: [{ id: 2, state: true, isAuto: false }],
  lights: [{ id: 3, state: true, isAuto: false }],
  userName: "Gilbert",
};

function rootReducer(state = initialState, action) {
  switch (action.type) {

    //window actions
    case 'SET_WINDOWS':
        return { ...state, windows: action.payload };
    case 'UPDATE_WINDOW_OPEN_STATE':
      return {
        ...state,
        windows: state.windows.map((window, index) =>
          index === action.payload.index ? { ...window, state: action.payload.state } : window
        ),
      };
    case 'UPDATE_WINDOW_BLOCKED_STATE':
        return {
          ...state,
          windows: state.windows.map((window, index) =>
            index === action.payload.index ? { ...window, state: action.payload.state } : window
          ),
    };

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
    case 'SET LIGHTS':
        return { ...state, lights: action.payload };
    case 'UPDATE_LIGHT_ON_STATE':
      return {
        ...state,
        lights: state.lights.map((light, index) =>
          index === action.payload.index ? { ...light, state: action.payload.state } : light
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
