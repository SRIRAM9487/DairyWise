import React from 'react';

const TableComponent = ({ columns, data }) => {

  return (
    <table className="w-full border-collapse  text-sm text-left text-gray-700  ">
      <thead className="bg-gray-100  uppercase text-xs tracking-wider">
        <tr >
          {columns.map((col, index) => (
            <th key={index} className="px-6 py-3 border-b">
              {col.header}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row, rowIndex) => (
          <tr key={rowIndex} className="hover:bg-gray-50 transition-colors">
            {columns.map((col, colIndex) => (
              <td key={colIndex} className="px-4 py-2 whitespace-nowrap border">
                {col.Cell ? col.Cell(row) : row[col.accessor]}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default TableComponent;

