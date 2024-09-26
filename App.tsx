/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {
  KeyboardAvoidingView,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  useColorScheme,
  View,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';


function App(): JSX.Element {
  const backgroundStyle = {
    backgroundColor: 'blue',
    flex: 1
    
  };

  return (
    <View style={backgroundStyle} behavior='height'>
      
      <ScrollView style={{flex: 1, backgroundColor: 'black'}}>
        <TextInput style={{ backgroundColor: 'green', padding: 50 }} keyboardType='default' />
        <TextInput style={{ backgroundColor: 'red', padding: 50 }} keyboardType='numeric' />

      </ScrollView>
      <View style={{ height: 300, backgroundColor: 'blue' }} />
    </View>
  );
}

export default App;
