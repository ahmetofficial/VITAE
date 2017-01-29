/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('GROUPS_RATES', {
    group_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'GROUPS',
        key: 'group_id'
      }
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    user_comment: {
      type: DataTypes.STRING,
      allowNull: true
    }
  }, {
    tableName: 'GROUPS_RATES'
  });
};
