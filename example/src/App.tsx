import * as React from 'react';
import { StyleSheet, View, Text, Alert } from 'react-native';
import SharingData from 'react-native-sharing-data';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    SharingData.multiply(3, 7).then(setResult);
    var urlTest = "content://com.reactnativesharingdata/data"
    // var urlTest = "content://com.reactnativesharingdata/data/1/#"

    SharingData.insertData(urlTest, "deviceId", "xxxxxxx").then((msg) => {
      var urlTest = "content://com.reactnativesharingdata/data/1/#"
      SharingData.checkContentExist(urlTest).then((msg) => {
        Alert.alert(`${msg}`)
      })
    })
    
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});