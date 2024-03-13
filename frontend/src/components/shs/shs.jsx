import { useState } from 'react';
import { Typography, Button,  Dialog, DialogTitle, DialogContent, DialogActions, TextField } from '@mui/material';

const SHS = () => {
    // State to hold the selected user type for adding a user
    const [ setSelectedUserToAdd] = useState('');
    // State to control the visibility of the dialog for adding a user
    const [openAddUserDialog, setOpenAddUserDialog] = useState(false);
    // State to hold the login name
    const [loginName, setLoginName] = useState('');
    // State to hold the login ID
    const [loginId, setLoginId] = useState('');
    // State to control the visibility of the dialog for login
    const [openLoginDialog, setOpenLoginDialog] = useState(false);

    // Function to handle opening the dialog for adding a user
    const handleOpenAddUserDialog = () => {
        setOpenAddUserDialog(true);
    };

    // Function to handle closing the dialog for adding a user
    const handleCloseAddUserDialog = () => {
        setOpenAddUserDialog(false);
    };

    // Function to handle selecting a user type from the dialog for adding a user
    const handleSelectUserToAdd = (userType) => {
        setSelectedUserToAdd(userType);
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
            {/* Login button to open login dialog */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenLoginDialog} variant="contained" style={{ width: '350px' }}>Login</Button>
            </div>
            {/* Add User button to open add user dialog */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenAddUserDialog} variant="contained" style={{ width: '350px' }}>Add User Profile</Button>
            </div>
            {/* Buttons for Delete and Edit User */}
            <div style={{ marginTop: '20px' }}>
                <Button variant="contained" style={{ width: '350px' }}>Delete User</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button variant="contained" style={{ width: '350px' }}>Edit User</Button>
            </div>
            {/* Buttons for other actions */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetDateTime} variant="contained" style={{ width: '350px' }}>Set Date and time</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetLocation} variant="contained" style={{ width: '350px' }}>Set house location</Button>
            </div>
            {/* Dialog for selecting user type to add */}
            <Dialog open={openAddUserDialog} onClose={handleCloseAddUserDialog}>
                <DialogTitle>Select User Type to Add</DialogTitle>
                <DialogContent>
                    <Button onClick={() => handleSelectUserToAdd('Child')} variant="contained" style={{ marginBottom: '10px', width: '350px' }}>Child</Button>
                    <Button onClick={() => handleSelectUserToAdd('Parent')} variant="contained" style={{ marginBottom: '10px', width: '350px' }}>Parent</Button>
                    <Button onClick={() => handleSelectUserToAdd('Guest')} variant="contained" style={{ marginBottom: '10px', width: '350px' }}>Guest</Button>
                    <Button onClick={() => handleSelectUserToAdd('Stranger')} variant="contained" style={{ marginBottom: '10px', width: '350px' }}>Stranger</Button>
                </DialogContent>
                <DialogActions>
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
                        id="name"
                        label="Name"
                        fullWidth
                        value={loginName}
                        onChange={(e) => setLoginName(e.target.value)}
                    />
                    <TextField
                        margin="dense"
                        id="id"
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
        </div>
    );
};

export default SHS;
