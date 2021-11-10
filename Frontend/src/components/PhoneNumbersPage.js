import React from "react";
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import {getCountries} from "../api/CountriesAPI";
import {getPhoneNumbers} from "../api/PhoneNumbersAPI";
import { MDBContainer, MDBRow, MDBCol } from "mdbreact";
import Multiselect from 'multiselect-react-dropdown';
import PaginatedTable from '../components/PaginatedTable';
import config from '../config/config'
import {mapObjectsToField} from '../utils/Utils'
const statesArray = [
  {
    displayName: 'Valid',
    identifier: 'VALID',
  }, 
  {
    displayName: 'Invalid',
    identifier: 'INVALID',
  },
];

const columns = [
  {
    name: 'Phone Number',
    selector: 'number',
  },
  {
    name: 'Country',
    selector: 'countryName',
  },
];
const TABLE_TITLE = 'Phone Numbers';
const COUNTRY_DISPLAY_NAME = 'name';
const STATES_DISPLAY_NAME = 'displayName';
const COUNTRY_IDENTIFIER = 'countryCode';
const STATE_IDENTIFIER = 'identifier';
function PhoneNumbersPage() {
  const [phonenumbers, setPhoneNumbers] = React.useState(null);
  const [availableCountries, setAvailableCountries] = React.useState([]);
  const [selectedCountriesFilter, setSelectedCountriesFilter] = React.useState([]);
  const [selectedStatusFilter, setSelectedStatusFilter] = React.useState([]);
  const [pageNumber, setPageNumber] = React.useState(1);
  const [resetDefaultPage, setResetDefaultPage] = React.useState(false);
  const handleCountriesFilter = (countries)=>{
    setPageNumber(1);
    setResetDefaultPage(true);
    setSelectedCountriesFilter(mapObjectsToField(countries, COUNTRY_IDENTIFIER));
  }
  const handleStatusFilter =  (states) =>{
    setPageNumber(1);
    setResetDefaultPage(true);
    setSelectedStatusFilter(mapObjectsToField(states, STATE_IDENTIFIER));
  }
  React.useEffect(() => {
    getCountries().then((response) => {
      setAvailableCountries(response.data);
    });
  }, []);
  React.useEffect(() => {
    const newFilter = 
    {
        'states': selectedStatusFilter ,
        'countriesCodes': selectedCountriesFilter,
        'pageSize': config.PAGE_SIZE,
        'pageNumber': pageNumber,
    };
    getPhoneNumbers(newFilter).then((response) => {
      setPhoneNumbers(response.data);
    });
  }, [selectedStatusFilter, selectedCountriesFilter, pageNumber]);
  if (!phonenumbers) {
    return null;
  }
  if(phonenumbers.errorMessage){
    return (
      <h1>{phonenumbers.errorMessage}</h1>
    )
  }
  return (
    <MDBContainer>
      <h1>Phone Numbers</h1>
      {renderFiltersComponent(availableCountries, handleCountriesFilter, handleStatusFilter)}
      {renderPaginatedTable(phonenumbers.phoneNumbers, phonenumbers.totalCount, setPageNumber, config.PAGE_SIZE, resetDefaultPage)}
    </MDBContainer>
  );
}

function renderFiltersComponent(availableCountries, handleCountriesFilter, handleStatusFilter){
  return (
    <div>
      <MDBContainer>
        <MDBRow>
          {renderFilterMultiSelect(COUNTRY_DISPLAY_NAME, availableCountries, true, handleCountriesFilter)}
          {renderFilterMultiSelect(STATES_DISPLAY_NAME, statesArray, true, handleStatusFilter)}
        </MDBRow>
        </MDBContainer>
    </div>
  );
}

function renderFilterMultiSelect(displayName, options, isObject, handleSelectionChange){
  return (
    <MDBCol>
      <Multiselect
        isObject={isObject}
        displayValue={displayName}
        options={options}
        onSelect={handleSelectionChange}
        onRemove={handleSelectionChange}
      />
    </MDBCol>);
}
function renderPaginatedTable(rows, totalCount, setPageNumber, pageSize, resetDefaultPage){
    return <PaginatedTable
        title={TABLE_TITLE}
        columns={columns}
        rows={rows}
        totalCount={totalCount}
        onChange={setPageNumber}
        countPerPage={pageSize}
        resetDefaultPage={resetDefaultPage}
      />
}

export default PhoneNumbersPage;
