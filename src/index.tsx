import { NativeModules } from 'react-native';

type SharingDataType = {
  multiply(a: number, b: number): Promise<number>;
  checkContentExist(url: string): Promise<boolean>;
  queryAllData(url: string): Promise<any>;
  queryDataByKey(url: string, key: String): Promise<any>;
  insertData(url: string, key: string, value: string): Promise<any>;
};

const { SharingData } = NativeModules;

export default SharingData as SharingDataType;
