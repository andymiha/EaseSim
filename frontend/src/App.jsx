import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Provider } from 'react-redux';  // Import Provider
import store from './store'; // Import the store
import Dashboard from './pages/Dashboard';
import SHS from './components/shs/shsNew';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { purple } from '@mui/material/colors';

// Import other pages as needed

const theme = createTheme({
  palette: {
    primary: {
      main: '#7986cb',
    },
    secondary: {
      main: '#f44336',
    },
  },
});

const App = () => {
  return (
    <Provider store={store}> {/* Wrap your components with Provider */}
      <ThemeProvider theme={theme}>
        <Router>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            {/* Add routes for other pages */}
          </Routes>
        </Router>
      </ThemeProvider>
    </Provider>
  );
};

export default App;
