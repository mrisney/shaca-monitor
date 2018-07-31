import React from 'react';
import { List, Datagrid, Filter, filters, TextInput, DateInput, TextField,DateField, RichTextField, Show, SimpleShowLayout, ShowButton} from 'react-admin';


const EntryFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search" source="q" alwaysOn />
        <TextInput label="errors" source="errors" defaultValue="Hello, World!" />
        <DateInput label="lastModified" source="lastModified" />
    </Filter>
);

export const FileList = (props) => (
    <List {...props} title="Nightly File Uploads" filters={<EntryFilter />}>
        <Datagrid>
            <TextField source="id" />        
            <TextField source="fileName" />
            <TextField source="errors" />
            <TextField source="description" />
            <DateField source="lastModified" showTime />
            <ShowButton />
        </Datagrid>
    </List>
);

export const FileShow = (props) => (
    <Show {...props} title="File Data Summary" >
        <SimpleShowLayout>
            <TextField source="metaData.main_data"/>
            <TextField source="metaData.mvc_major_report"/>
            <TextField source="metaData.mvc_major_gps"/>
            <TextField source="metaData.units_major"/>
            <TextField source="metaData.address" />
            <TextField source="metaData.object" />
            <TextField source="metaData.vehicles" />
            <TextField source="metaData.persons" />
            <TextField source="metaData.phone" />
            <TextField source="metaData.identification" />    
        </SimpleShowLayout>
    </Show>
);