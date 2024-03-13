import React, { useState } from 'react';
import { Typography, Button, Select, MenuItem } from '@mui/material';

const SHS = ({
                 handleAddEditProfiles,
                 handleSetDateTime,
                 handleSetLocation,
                 handleLogin
             }) => {
    // State to hold the selected user type
    const [selectedUserType, setSelectedUserType] = useState('');

    // Logic for deleting a user profile
    const handleDeleteProfile = () => {
        // Show confirmation dialog before deleting profile
        if (window.confirm('Are you sure you want to delete this user profile?')) {
            // Call function to delete profile
            // Example: deleteUserProfile(profileId);
            console.log('Deleting user profile...');
        }
    };

    // Logic for setting date and time
    const handleSetDateTime = () => {
        const currentDate = new Date();
        // Logic for setting date and time goes here
        console.log('Setting date and time:', currentDate);
    };

    // Logic for setting house location
    const handleSetLocation = () => {
        // Logic for setting house location goes here
        console.log('Setting house location...');
    };

    // Logic for adding/removing/editing user profiles
    const handleAddEditProfiles = (event) => {
        // Handle the selected action based on the event value
        const action = event.target.value;
        switch (action) {
            case 'add':
                // Logic for adding a user profile
                console.log('Adding a new user profile...');
                break;
            case 'remove':
                // Call the handleDeleteProfile function
                handleDeleteProfile();
                break;
            case 'edit':
                // Logic for editing a user profile
                console.log('Editing user profile...');
                break;
            default:
                break;
        }
    };

    // Logic for handling login
    const handleLogin = (event) => {
        // Set the selected user type to the value of the event
        setSelectedUserType(event.target.value);
        // Additional logic for handling login
        console.log('Logging in with user type:', event.target.value);
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Typography variant="h3">SHS</Typography>
            {/* Dropdown for Login and User Types */}
            <div style={{ marginTop: '20px' }}>
                <Select
                    onChange={handleLogin}
                    value={selectedUserType}
                    style={{ width: '400px' }}
                >
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
                    <MenuItem value="remove">Remove user profile</MenuItem>
                    <MenuItem value="edit">Edit user profile</MenuItem>
                </Select>
            </div>
        </div>
    );
};

export default SHS;
