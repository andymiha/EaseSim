import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Typography, Button, Dialog, DialogTitle, DialogContent, DialogActions, TextField, Select, MenuItem } from '@mui/material';

const SHS = () => {
    const dispatch = useDispatch();
    // State to hold the selected user type for adding a user
    const [selectedUserType, setSelectedUserType] = useState('');
    const [addUserName, setAddUserName] = useState('');
    const [addUserId, setAddUserId] = useState('');
    const [houseLocation, setHouseLocation] = useState('');
    const [openAddUserDialog, setOpenAddUserDialog] = useState(false);
    const [openLoginDialog, setOpenLoginDialog] = useState(false);
    const [loginName, setLoginName] = useState('');
  
    const [openSetLocationDialog, setOpenSetLocationDialog] = useState(false);
    const [openDeleteUserDialog, setOpenDeleteUserDialog] = useState(false);
    const [deleteUserId, setDeleteUserId] = useState('');

    const handleOpenAddUserDialog = () => {
        setOpenAddUserDialog(true);
    };

    const handleCloseAddUserDialog = () => {
        setOpenAddUserDialog(false);
    };

    const handleSelectUserType = (event) => {
        setSelectedUserType(event.target.value);
    };

    const handleAddUser = () => {
        console.log('Adding user of type:', selectedUserType, 'with name:', addUserName, 'and ID:', addUserId);
        setSelectedUserType('');
        setAddUserName('');
        setAddUserId('');
        setOpenAddUserDialog(false);
    };

    const handleEditUser = () => {
        console.log('Editing user...');
    };

    const handleOpenLoginDialog = () => {
        setOpenLoginDialog(true);
    };

    const handleCloseLoginDialog = () => {
        setOpenLoginDialog(false);
    };

    //logging in functions

    const handleLogin = () => {
        dispatch({ type: 'SET_WINDOWS', payload: [{ jonIsCool: true, id: "jesus" }] });
        // Additional logic for handling login
        console.log('Logging in with name:', loginName, 'and ID:', loginId);
    };

    const handleSetDateTime = () => {
        const currentTime = new Date().toLocaleTimeString();
        console.log('Setting current time:', currentTime);
    };

    const handleOpenSetLocationDialog = () => {
        setOpenSetLocationDialog(true);
    };

    const handleCloseSetLocationDialog = () => {
        setOpenSetLocationDialog(false);
    };

    const handleSaveHouseLocation = () => {
        console.log('Setting house location:', houseLocation);
        setHouseLocation('');
        handleCloseSetLocationDialog();
    };

    const handleOpenDeleteUserDialog = () => {
        setOpenDeleteUserDialog(true);
    };

    const handleCloseDeleteUserDialog = () => {
        setOpenDeleteUserDialog(false);
    };

    const handleDeleteUser = () => {
        console.log('Deleting user with ID:', deleteUserId);
        setDeleteUserId('');
        handleCloseDeleteUserDialog();
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Typography variant="h3">SHS</Typography>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenLoginDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Login</Button>
            </div>
           <div style={{ marginTop: '20px' }}>
    <Button onClick={handleOpenAddUserDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Add User</Button>
</div>
<div style={{ marginTop: '20px' }}>
    <Button onClick={handleOpenAddUserDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Edit User</Button>
</div>

            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenDeleteUserDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Delete User</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleSetDateTime} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Set Date and time</Button>
            </div>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenSetLocationDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Set house location</Button>
            </div>

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
                    <Button onClick={handleEditUser} color="primary">Edit</Button>
                </DialogActions>
            </Dialog>
            <Dialog open={openLoginDialog} onClose={handleCloseLoginDialog}>
    <DialogTitle>Login</DialogTitle>
    <DialogContent>
        <Select
            autoFocus
            value={loginName}
            onChange={(e) => setLoginName(e.target.value)}
            fullWidth
            displayEmpty
            style={{ marginBottom: '20px' }}
        >
            <MenuItem value="" disabled>Select Name</MenuItem>
            <MenuItem value="John">John</MenuItem>
            <MenuItem value="Alice">Alice</MenuItem>
            <MenuItem value="Bob">Bob</MenuItem>
            <MenuItem value="Jane">Jane</MenuItem>
        </Select>
    </DialogContent>
    <DialogActions>
        <Button onClick={handleLogin} color="primary">Login</Button>
        <Button onClick={handleCloseLoginDialog} color="primary">Cancel</Button>
    </DialogActions>
</Dialog>


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
            </
            Dialog>

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
