import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
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
    <ThemeProvider theme={theme}>
      <Router>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="login" element={<SHS />} />
          {/* Add routes for other pages */}
        </Routes>
      </Router>
    </ThemeProvider>
  );
};

export default App;
