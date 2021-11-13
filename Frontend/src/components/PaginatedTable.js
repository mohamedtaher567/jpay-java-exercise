import React from 'react';
import DataTable from 'react-data-table-component';

function PaginatedTable (props) {
  const rows = props.rows;
  const columns = props.columns;
  return (
    <div className="App">
      <DataTable
        title={props.title? props.title : 'Table'}
        columns={columns}
        data={rows}
        highlightOnHover
        pagination
        paginationServer
        paginationTotalRows={props.totalCount}
        paginationPerPage={props.countPerPage}
        currentPage={props.currentPage}
        paginationComponentOptions={{
          noRowsPerPage: true
        }}
        onChangePage={page => props.onChange(page)}
        paginationResetDefaultPage={props.resetDefaultPage}
      />
    </div>
  );
}

export default PaginatedTable;
