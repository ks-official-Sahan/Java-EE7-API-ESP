const callESP = async (status) => {
    try {
        console.log(`Sending status: ${status}`);
        const response = await fetch(`http://192.168.1.80?status=${status}`);
        console.log(response);
        if (!response.ok) {
        console.log("Not OK");
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        console.log("OK");
        const data = await response.text(); // Use `text` if the response is not JSON
        console.log(data);
        console.log('Response:', data);
    } catch (error) {
        console.error('Error fetching data:', error.message);
    }
};
