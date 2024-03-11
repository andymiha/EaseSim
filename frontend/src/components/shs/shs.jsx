import React from 'react';
import { Typography, Button, Select, MenuItem } from '@mui/material';

const SHS = ({
                 handleAddEditProfiles,
                 handleSetDateTime,
                 handleLogin,
                 handleSetLocation
             }) => {
    const handleDeleteProfile = () => {
        // Show confirmation dialog before deleting profile
        if (window.confirm('Are you sure you want to delete this user profile?')) {
            // Call function to delete profile
            // Example: deleteUserProfile(profileId);
        }
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Typography variant="h3">SHS</Typography>
            {/* Dropdown for Login and User Types */}
            <div style={{ marginTop: '20px' }}>
                <Select onChange={handleLogin} value="" style={{ width: '400px' }}>
                    {/* Placeholder option */}
                    <MenuItem value="" disabled hidden>Select User Type</MenuItem>
                    {/* Actual options */}
                    <MenuItem value="Stranger">Stranger</MenuItem>
                    <MenuItem value="Guest">Guest</MenuItem>
                    <MenuItem value="Parent">Parent</MenuItem>
                    <MenuItem value="Child">Child</MenuItem>
                </Select>
            </div>
            {/* Buttons for other actions */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetDateTime} variant="contained" style={{ width: '400px' }}>Set Date and time</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetLocation} variant="contained" style={{ width: '400px' }}>Set house location</Button>
            </div>
            {/* Dropdown for Add/remove/edit user profiles */}
            <div style={{ marginTop: '20px' }}>
                <Select onChange={handleAddEditProfiles} value="" style={{ width: '400px' }}>
                    {/* Placeholder option */}
                    <MenuItem value="" disabled hidden>Select a User Action</MenuItem>
                    {/* Actual options */}
                    <MenuItem value="add">Add user profile</MenuItem>
                    <MenuItem value="remove" onClick={handleDeleteProfile}>Remove user profile</MenuItem>
                    <MenuItem value="edit">Edit user profile</MenuItem>
                </Select>
            </div>
        </div>
    );
};

export default SHS;
