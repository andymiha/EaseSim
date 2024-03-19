import { useState, useEffect } from 'react';

const useDataFetching = (url, method = 'GET', body = null) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log('Fetching data from:', url);
    console.log('Method:', method);
    console.log('Body:', body);

    const fetchData = async () => {
      try {
        const options = {
          method,
          headers: {
            'Content-Type': 'application/json'
          },
          body: body ? JSON.stringify(body) : null
        };

        const response = await fetch(url, options);

        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }

        const result = await response.json();
        setData(result);
      } catch (error) {
        console.error('Error fetching data:', error);
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url, method, body]);

  return { data, error, loading };
};

export default useDataFetching;
