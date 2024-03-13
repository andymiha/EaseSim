import { useState } from 'react';
import { Typography, Button,  Dialog, DialogTitle, DialogContent, DialogActions, TextField, Select, MenuItem } from '@mui/material';

const SHS = () => {
    // State to hold the selected user type for adding a user
    const [selectedUserType, setSelectedUserType] = useState('');
    // State to hold the name of the user to add
    const [addUserName, setAddUserName] = useState('');
    // State to hold the ID of the user to add
    const [addUserId, setAddUserId] = useState('');
    // State to control the visibility of the dialog for adding a user
    const [openAddUserDialog, setOpenAddUserDialog] = useState(false);
    // State to control the visibility of the dialog for login
    const [openLoginDialog, setOpenLoginDialog] = useState(false);
    // State to hold the login name
    const [loginName, setLoginName] = useState('');
    // State to hold the login ID
    const [loginId, setLoginId] = useState('');

    // Function to handle opening the dialog for adding a user
    const handleOpenAddUserDialog = () => {
        setOpenAddUserDialog(true);
    };

    // Function to handle closing the dialog for adding a user
    const handleCloseAddUserDialog = () => {
        setOpenAddUserDialog(false);
    };

    // Function to handle selecting a user type from the dropdown for adding a user
    const handleSelectUserType = (event) => {
        setSelectedUserType(event.target.value);
    };

    // Function to handle adding a user
    const handleAddUser = () => {
        // Logic to add the user using the selectedUserType, addUserName, and addUserId
        console.log('Adding user of type:', selectedUserType, 'with name:', addUserName, 'and ID:', addUserId);
        // Clear the input fields after adding the user
        setSelectedUserType('');
        setAddUserName('');
        setAddUserId('');
        setOpenAddUserDialog(false);
    };

    // Function to handle opening the dialog for login
    const handleOpenLoginDialog = () => {
        setOpenLoginDialog(true);
    };

    // Function to handle closing the dialog for login
    const handleCloseLoginDialog = () => {
        setOpenLoginDialog(false);
    };

    // Function to handle login
    const handleLogin = () => {
        // Additional logic for handling login
        console.log('Logging in with name:', loginName, 'and ID:', loginId);
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

    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Typography variant="h3">SHS</Typography>
            {/* Login button to open the dialog for login */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenLoginDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Login</Button>
            </div>
            {/* Add User button to open the dialog for adding a user */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenAddUserDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Add User Profile</Button>
            </div>
            {/* Buttons for Delete and Edit User */}
            <div style={{ marginTop: '20px' }}>
                <Button variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Delete User</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Edit User</Button>
            </div>
            {/* Dialog for adding a user */}
            <Dialog open={openAddUserDialog} onClose={handleCloseAddUserDialog}>
                <DialogTitle>Add User</DialogTitle>
                <DialogContent>
                    <Select
                        value={selectedUserType}
                        onChange={handleSelectUserType}
                        fullWidth
                        variant="outlined"
                        style={{ marginBottom: '20px' }}
                    >
                        <MenuItem value="">Select User Type</MenuItem>
                        <MenuItem value="Child">Child</MenuItem>
                        <MenuItem value="Parent">Parent</MenuItem>
                        <MenuItem value="Guest">Guest</MenuItem>
                        <MenuItem value="Stranger">Stranger</MenuItem>
                    </Select>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="addUserName"
                        label="Name"
                        fullWidth
                        value={addUserName}
                        onChange={(e) => setAddUserName(e.target.value)}
                        style={{ marginBottom: '20px' }}
                    />
                    <TextField
                        margin="dense"
                        id="addUserId"
                        label="ID"
                        fullWidth
                        value={addUserId}
                        onChange={(e) => setAddUserId(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleAddUser} color="primary" disabled={!selectedUserType || !addUserName || !addUserId}>Add</Button>
                    <Button onClick={handleCloseAddUserDialog} color="primary">Cancel</Button>
                </DialogActions>
            </Dialog>
            {/* Dialog for login */}
            <Dialog open={openLoginDialog} onClose={handleCloseLoginDialog}>
                <DialogTitle>Login</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="loginName"
                        label="Name"
                        fullWidth
                        value={loginName}
                        onChange={(e) => setLoginName(e.target.value)}
                        style={{ marginBottom: '20px' }}
                    />
                    <TextField
                        margin="dense"
                        id="loginId"
                        label="ID"
                        fullWidth
                        value={loginId}
                        onChange={(e) => setLoginId(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleLogin} color="primary">Login</Button>
                    <Button onClick={handleCloseLoginDialog} color="primary">Cancel</Button>
                </DialogActions>
            </Dialog>
            {/* Buttons for other actions */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetDateTime} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Set Date and time</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetLocation} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Set house location</Button>
            </div>
        </div>
    );
};

export default SHS;
