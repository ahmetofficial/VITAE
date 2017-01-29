/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('EVENTS_RATES', {
    event_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'EVENTS',
        key: 'event_id'
      }
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    }
  }, {
    tableName: 'EVENTS_RATES'
  });
};
