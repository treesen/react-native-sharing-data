import { NativeModules } from 'react-native';

type SharingDataType = {
  set(
    defaultSuiteName: string | null,
    key: string,
    value: string
  ): Promise<void>;
  get(defaultSuiteName: string | null, key: string): Promise<string>;
  clear(defaultSuiteName: string | null, key: string): Promise<void>;
  getMultiple(
    defaultSuiteName: string | null,
    keys: [string]
  ): Promise<[string]>;
  setMultiple(defaultSuiteName: string | null, data: [{}]): Promise<void>;
  clearMultiple(defaultSuiteName: string | null, keys: [string]): Promise<void>;

  checkContentExist(url: string): Promise<boolean>;
  queryAllData(url: string): Promise<any>;
  queryDataByKey(url: string, key: String): Promise<any>;
  insertData(url: string, key: string, value: string): Promise<any>;
};

const { SharingData } = NativeModules;

export default SharingData as SharingDataType;
