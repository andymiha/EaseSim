import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Typography, Button, Dialog, DialogTitle, DialogContent, DialogActions, TextField, Select, MenuItem } from '@mui/material';

const SHS = () => {
    const dispatch = useDispatch();
    // State to hold the selected user type for adding a user
    const [selectedUserType, setSelectedUserType] = useState('');
    // State to hold the name of the user to add
    const [addUserName, setAddUserName] = useState('');
    // State to hold the ID of the user to add
    const [addUserId, setAddUserId] = useState('');
    // State to hold the house location
    const [houseLocation, setHouseLocation] = useState('');
    // State to control the visibility of the dialog for adding a user
    const [openAddUserDialog, setOpenAddUserDialog] = useState(false);
    // State to control the visibility of the dialog for login
    const [openLoginDialog, setOpenLoginDialog] = useState(false);
    // State to hold the login name
    const [loginName, setLoginName] = useState('');
    // State to hold the login ID
    const [loginId, setLoginId] = useState('');
    // State to control the visibility of the dialog for setting house location
    const [openSetLocationDialog, setOpenSetLocationDialog] = useState(false);
    // State to control the visibility of the dialog for deleting a user
    const [openDeleteUserDialog, setOpenDeleteUserDialog] = useState(false);
    // State to hold the ID of the user to delete
    const [deleteUserId, setDeleteUserId] = useState('');

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

    const handleEditUser = () => {
        // Logic to edit the user goes here
        console.log('Editing user...');
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
        dispatch({ type: 'SET_WINDOWS', payload: [{ jonIsCool: true, id: "jesus" }] });
        // Additional logic for handling login
        console.log('Logging in with name:', loginName, 'and ID:', loginId);
    };

    // Function to handle setting date and time
    const handleSetDateTime = () => {
        const currentTime = new Date().toLocaleTimeString();
        // Logic for setting date and time goes here
        console.log('Setting current time:', currentTime);
    };

    // Function to handle opening the dialog for setting house location
    const handleOpenSetLocationDialog = () => {
        setOpenSetLocationDialog(true);
    };

    // Function to handle closing the dialog for setting house location
    const handleCloseSetLocationDialog = () => {
        setOpenSetLocationDialog(false);
    };

    // Function to handle saving the house location
    const handleSaveHouseLocation = () => {
        // Logic for saving the house location
        console.log('Setting house location:', houseLocation);
        // Clear the house location input field
        setHouseLocation('');
        // Close the dialog
        handleCloseSetLocationDialog();
    };

    // Function to handle opening the dialog for deleting a user
    const handleOpenDeleteUserDialog = () => {
        setOpenDeleteUserDialog(true);
    };

    // Function to handle closing the dialog for deleting a user
    const handleCloseDeleteUserDialog = () => {
        setOpenDeleteUserDialog(false);
    };

    // Function to handle deleting a user
    const handleDeleteUser = () => {
        // Logic to delete the user using the deleteUserId
        console.log('Deleting user with ID:', deleteUserId);
        // Clear the deleteUserId state
        setDeleteUserId('');
        // Close the delete user dialog
        handleCloseDeleteUserDialog();
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
                <Button onClick={handleOpenAddUserDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Add or Edit User Profile</Button>
            </div>
            {/* Delete User button to open the dialog for deleting a user */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenDeleteUserDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Delete User</Button>
            </div>
            {/* Buttons for other actions */}
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetDateTime} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Set Date and time</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenSetLocationDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Set house location</Button>
            </div>

            {/* Dialog for adding a user */}
            <Dialog open={openAddUserDialog} onClose={handleCloseAddUserDialog}>
             
            <Dialog open={openAddUserDialog} onClose={handleCloseAddUserDialog}>
    <DialogTitle>Add or Edit User</DialogTitle>
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
            type="number"
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
        {/* New "Edit" button */}
        <Button onClick={handleEditUser} color="primary">Edit</Button>
    </DialogActions>
</Dialog>
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
                        type="number"
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

            {/* Dialog for setting house location */}
            <Dialog open={openSetLocationDialog} onClose={handleCloseSetLocationDialog}>
                <DialogTitle>Set House Location</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="houseLocation"
                        label="House Location"
                        fullWidth
                        value={houseLocation}
                        onChange={(e) => setHouseLocation(e.target.value)}
                        style={{ marginBottom: '20px' }}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleSaveHouseLocation} color="primary">Save</Button>
                    <Button onClick={handleCloseSetLocationDialog} color="primary">Cancel</Button>
                </DialogActions>
            </Dialog>

            {/* Dialog for deleting a user */}
            <Dialog open={openDeleteUserDialog} onClose={handleCloseDeleteUserDialog}>
                <DialogTitle>Delete User</DialogTitle>
                <DialogContent>
                    <TextField
                        type="number"
                        autoFocus
                        margin="dense"
                        id="deleteUserId"
                        label="User ID"
                        fullWidth
                        value={deleteUserId}
                        onChange={(e) => setDeleteUserId(e.target.value)}
                        style={{ marginBottom: '20px' }}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleDeleteUser} color="primary">Delete</Button>
                    <Button onClick={handleCloseDeleteUserDialog} color="primary">Cancel</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default SHS;
