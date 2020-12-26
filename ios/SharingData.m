
#import "SharingData.h"

@implementation SharingData

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

- (NSUserDefaults *) getDefaultUser: (NSString *)defaultSuiteName {
    if(defaultSuiteName == nil){
        return [NSUserDefaults standardUserDefaults];
    } else {
        return [[NSUserDefaults alloc] initWithSuiteName:defaultSuiteName];
    }
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(get:(NSString *)defaultSuiteName: (NSString *)key
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    resolve([[self getDefaultUser:defaultSuiteName] stringForKey:key]);
}

RCT_EXPORT_METHOD(set:(NSString *)defaultSuiteName: (NSString *)key value:(NSString *)value
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    [[self getDefaultUser:defaultSuiteName] setObject:value forKey:key];
    resolve([NSNull null]);
}

RCT_EXPORT_METHOD(clear:(NSString *)defaultSuiteName: (NSString *)key
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    [[self getDefaultUser:defaultSuiteName] removeObjectForKey:key];
    resolve([NSNull null]);
}

RCT_EXPORT_METHOD(getMultiple:(NSString *)defaultSuiteName: (NSArray *)keys
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    NSMutableArray *result = [NSMutableArray array];
    for(NSString *key in keys) {
        NSString *value = [[self getDefaultUser:defaultSuiteName] stringForKey:key];
        [result addObject: value == nil ? [NSNull null] : value];
    }
    resolve(result);
}

RCT_EXPORT_METHOD(setMultiple:(NSString *)defaultSuiteName: (NSDictionary *)data
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    for (id key in data) {
        [[self getDefaultUser:defaultSuiteName] setObject:[data objectForKey:key] forKey:key];
    }
    resolve([NSNull null]);
}

RCT_EXPORT_METHOD(clearMultiple:(NSString *)defaultSuiteName: (NSArray *)keys
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    for(NSString *key in keys) {
        [[self getDefaultUser:defaultSuiteName] removeObjectForKey:key];
    }
    resolve([NSNull null]);
}

RCT_EXPORT_METHOD(getAll:(NSString *)defaultSuiteName: (RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
    NSArray *keys = [[[self getDefaultUser:defaultSuiteName] dictionaryRepresentation] allKeys];
    NSMutableDictionary *result = [NSMutableDictionary dictionary];
    for(NSString *key in keys) {
        NSString *value = [[self getDefaultUser:defaultSuiteName] stringForKey:key];
        result[key] = value == nil ? [NSNull null] : value;
    }
    resolve(result);
}

RCT_EXPORT_METHOD(clearAll:(NSString *)defaultSuiteName: (RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
    NSArray *keys = [[[self getDefaultUser:defaultSuiteName] dictionaryRepresentation] allKeys];
    [self clearMultiple:defaultSuiteName:keys resolve:resolve reject:reject];
}

@end
