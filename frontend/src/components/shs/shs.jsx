import { useState, useEffect } from 'react';
import {
    Typography,
    Button,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    List,
    ListItem,
    ListItemText,
} from '@mui/material';
import axios from 'axios';


const SHS = () => {
    const [openLoginDialog, setOpenLoginDialog] = useState(false);
    const [users, setUsers] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState('');

    axios.get('/usersdata')
    .then(response => {
        console.log(response);
        // Check if response.data is an array before setting users
        if (Array.isArray(response.data)) {
            setUsers(response.data); // Assuming response.data is an array of user objects
        } else {
            console.error('Users data is not an array:', response.data);
        }
    })
    .catch(error => {
        console.error('Error fetching user data:', error);
    });

    const handleOpenLoginDialog = () => {
        setOpenLoginDialog(true);
    };

    const handleCloseLoginDialog = () => {
        setOpenLoginDialog(false);
    };

    const handleLogin = () => {
        // Handle login logic for the selected user
        console.log('Logged in as user with ID:', selectedUserId);
        setSelectedUserId(''); // Reset selectedUserId
        setOpenLoginDialog(false);
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <Typography variant="h3">SHS</Typography>
            <div style={{ marginTop: '20px' }}>
                <Button onClick={handleOpenLoginDialog} variant="contained" style={{ width: '350px', marginBottom: '10px' }}>Login</Button>
            </div>

            {/* Login Dialog */}
            <Dialog open={openLoginDialog} onClose={handleCloseLoginDialog}>
                <DialogTitle>Select User to Login</DialogTitle>
                <DialogContent>
                    {/* Conditional rendering for users */}
                    {Array.isArray(users) && users.length > 0 ? (
                        <List>
                            {users.map(user => (
                                <ListItem button key={user.id} onClick={() => setSelectedUserId(user.id)}>
                                    <ListItemText primary={user.name} />
                                </ListItem>
                            ))}
                        </List>
                    ) : (
                        <Typography>No users available</Typography>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleLogin} color="primary" disabled={!selectedUserId}>Login</Button>
                    <Button onClick={handleCloseLoginDialog} color="primary">Cancel</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default SHS;
