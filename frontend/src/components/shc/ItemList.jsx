import { DataGrid } from '@mui/x-data-grid';

const columns = [
    { field: 'room', headerName: 'Room', width: 300 },
    { field: 'functionality', headerName: 'On/Off', width: 300 },
  ];
  
  const rows = [
    { id: 1, lightName: 'livingRoom' },
    { id: 2, lightName: 'bedroomOne' },
    { id: 3, lightName: 'bedroomTwo' },
    { id: 4, lightName: 'kitchen' },
    { id: 5, lightName: 'bathroom'},
    { id: 6, lightName: 'entrance'},
    { id: 7, lightName: 'backyard' },
    { id: 8, lightName: 'garage' },
    { id: 9, lightName: 'Roxie' },
  ];

const ItemList = () => {
    return (
        <div style={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={rows}
          columns={columns}
          initialState={{
            pagination: {
              paginationModel: { page: 0, pageSize: 5 },
            },
          }}
          pageSizeOptions={[5, 10]}
          checkboxSelection
        />
      </div>
    

        
    );
}
 
export default ItemList;