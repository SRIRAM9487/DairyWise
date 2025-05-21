import TableComponent from "../../Components/Table";

function Managers() {

  const data = Array.from({ length: 100 }, (_, index) => ({
    id: index + 1,
    name: `USER ${index + 1}`,
    email: `EAMIL${index + 1}@example.com`,
  }));
const columns = [
    { Header: 'Name', accessor: 'name' },
    { Header: 'Email', accessor: 'email' },
    {
      Header: 'Actions',
      accessor: 'actions',
      Cell: (row) => (
        <>
          <button
            onClick={() => {
              setSelectedRow(row);
              setIsModalOpen(true);
            }}
            className="text-blue-500 mr-2"
          >
            View
          </button>
        </>
      )
    }
  ];

  return (
    <div className="w-full h-[1000px] border border-black overflow-auto" >
      <TableComponent data={data} columns={columns} />
    </div>
  )
}

export default Managers
