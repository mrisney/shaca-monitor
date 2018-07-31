import React from 'react';
import { List, Datagrid, TextField } from 'react-admin';

export const GreetingsList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="content" />
        </Datagrid>
    </List>
);