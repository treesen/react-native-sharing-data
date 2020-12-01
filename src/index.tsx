import { NativeModules } from 'react-native';

type SharingDataType = {
  multiply(a: number, b: number): Promise<number>;
};

const { SharingData } = NativeModules;

export default SharingData as SharingDataType;
