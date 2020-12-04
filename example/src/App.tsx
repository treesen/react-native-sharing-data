import * as React from 'react';
import { StyleSheet, View, Text } from 'react-native';
import SharingData from 'react-native-sharing-data';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    SharingData.multiply(3, 7).then(setResult);
    // var urlTest = 'content://com.reactnativesharingdata/data';
    // // var urlTest = "content://com.reactnativesharingdata/data/1/#"

    var urlTest = 'content://com.reactnativesharingdata/data';

    //  SharingData.insertData(urlTest, 'test', 'xxxxxxx1').then(() => {
    //   SharingData.queryDataByKey(urlTest, "test").then((msg) => {
    //     console.log(msg)
    //     alert(JSON.stringify(msg));
    //   });
    // });

    SharingData.queryAllData(urlTest).then((msg) => {
      console.log(msg);
      // alert(JSON.stringify(msg));
    });
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
