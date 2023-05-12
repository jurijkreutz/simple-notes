import { useState } from 'react';
import Alert from '@mui/material/Alert';

export default function InfoBox( {text} ) {
  const [open, setOpen] = useState(true);

  return (
    <Alert severity='warning' sx={{ marginBottom: '25px' }}>
      {text}
    </Alert>
  );
}