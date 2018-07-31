import React from 'react';
import { Admin, Resource } from 'react-admin';
import { FileList, FileShow } from './hpdfiles';
import { UpdateList,UpdateSummary } from './kpdspillmanupdates';

import simpleRestProvider from 'ra-data-simple-rest';

const App = () => (
    <Admin title="SHACA E-Transfer Administration" dataProvider={simpleRestProvider('http://localhost:8080/')}>
        <Resource name="hpdfiles" options={{ label: 'HPD Nightly FTP' }} show={FileShow} list={FileList} />
        <Resource name="kpdspillmanupdates" options={{ label: 'KPD Spillman Updates' }} show={UpdateSummary} list={UpdateList} />  
    </Admin>
);

export default App;
