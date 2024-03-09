import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import { ThemeProvider, createTheme } from '@mui/material/styles';

// Import other pages as needed

const theme = createTheme();

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <Router>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          {/* Add routes for other pages */}
        </Routes>
      </Router>
    </ThemeProvider>
  );
};

export default App;
