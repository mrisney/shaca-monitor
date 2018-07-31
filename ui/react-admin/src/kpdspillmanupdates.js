import React from 'react';
import { ReferenceArrayField, ReferenceManyField,SingleFieldList, ChipField, List, Datagrid, Filter, filters, TextInput, DateInput, TextField,DateField, RichTextField, Show, SimpleShowLayout, ShowButton} from 'react-admin';


const UpdateFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search" source="q" alwaysOn />
        <TextInput label="errors" source="errors" defaultValue="Hello, World!" />
        <DateInput label="lastModified" source="lastModified" />
    </Filter>
);

export const UpdateList = (props) => (
    <List {...props} title="Nightly KPD Spillman Updates" filters={<UpdateFilter />}>
        <Datagrid>
            <TextField source="id" />        
            <TextField source="description" />
            <TextField source="successFail" />     
            <TextField source="errors" />
            <TextField source="numberOfAccidents" />
            <TextField source="numberOfUpdates" />
            <DateField source="lastModified" showTime />
            <ShowButton />
        </Datagrid>
    </List>
);

export const UpdateSummary = (props) => (
    <Show {...props} title="Update Summary" >
        <SimpleShowLayout>
            <TextField source="metaData.updateCount"/>
            <TextField source="metaData.newAccidentCount"/>
            <ReferenceArrayField addLabel={false} reference="metaData.accidents" target="accidentNumber">
                <Datagrid>
                    <DateField source="dateOfAccident" />
                    <TextField source="accidentNumber"/>
                    <TextField source="action"/>
                </Datagrid>
            </ReferenceArrayField>
        </SimpleShowLayout>
    </Show>
);

